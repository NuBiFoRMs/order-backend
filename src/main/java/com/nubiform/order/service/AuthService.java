package com.nubiform.order.service;

import com.nubiform.order.domain.Member;
import com.nubiform.order.repository.MemberRepository;
import com.nubiform.order.vo.request.SignUpRequest;
import com.nubiform.order.vo.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberRepository memberRepository;

    private final ModelMapper modelMapper;

    public MemberResponse signUp(SignUpRequest signUpRequest) {
        Member member = modelMapper.map(signUpRequest, Member.class);
        memberRepository.save(member);
        return modelMapper.map(member, MemberResponse.class);
    }
}
