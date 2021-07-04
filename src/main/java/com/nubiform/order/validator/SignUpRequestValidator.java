package com.nubiform.order.validator;

import com.nubiform.order.repository.MemberRepository;
import com.nubiform.order.vo.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class SignUpRequestValidator implements Validator {

    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SignUpRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignUpRequest signUpRequest = (SignUpRequest) target;
        if (memberRepository.existsByNickname(signUpRequest.getNickname())) {
            errors.rejectValue("nickname", "invalid.nickname", new Object[]{signUpRequest.getNickname()}, "이미 사용중인 닉네임입니다.");
        }
        if (memberRepository.existsByEmail(signUpRequest.getEmail())) {
            errors.rejectValue("email", "invalid.email", new Object[]{signUpRequest.getEmail()}, "이미 사용중인 이메일입니다.");
        }
    }
}
