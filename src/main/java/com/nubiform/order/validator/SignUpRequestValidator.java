package com.nubiform.order.validator;

import com.nubiform.order.repository.MemberRepository;
import com.nubiform.order.vo.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class SignUpRequestValidator implements Validator {

    private final MemberRepository memberRepository;

    private final MessageSource validationMessageSource;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SignUpRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignUpRequest signUpRequest = (SignUpRequest) target;
        if (memberRepository.existsByNickname(signUpRequest.getNickname())) {
            errors.rejectValue("nickname", "Duplicate", new Object[]{signUpRequest.getNickname()}, "duplicate nickname");
        }
        if (memberRepository.existsByEmail(signUpRequest.getEmail())) {
            errors.rejectValue("email", "Duplicate", new Object[]{signUpRequest.getEmail()}, "duplicate email");
        }
    }
}
