package com.nubiform.order.service;

import com.nubiform.order.config.security.jwt.JwtTokenProvider;
import com.nubiform.order.domain.Account;
import com.nubiform.order.domain.Member;
import com.nubiform.order.repository.MemberRepository;
import com.nubiform.order.vo.request.SignInRequest;
import com.nubiform.order.vo.request.SignUpRequest;
import com.nubiform.order.vo.response.MemberResponse;
import com.nubiform.order.vo.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService implements UserDetailsService {

    private final MemberRepository memberRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    public MemberResponse signUp(SignUpRequest signUpRequest) {
        Member member = modelMapper.map(signUpRequest, Member.class);
        member.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        Member newMember = memberRepository.save(member);
        return modelMapper.map(newMember, MemberResponse.class);
    }

    public TokenResponse signIn(SignInRequest signInRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(signInRequest.getUserid(), signInRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.createToken(authentication);

        log.debug("success login: {}", token);

        return TokenResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("loadUserByUsername: {}", username);
        Member member = memberRepository.findByNicknameOrEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new Account(member);
    }
}
