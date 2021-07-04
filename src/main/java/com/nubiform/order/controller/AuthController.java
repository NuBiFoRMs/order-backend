package com.nubiform.order.controller;

import com.nubiform.order.exception.ApiParameterException;
import com.nubiform.order.service.AuthService;
import com.nubiform.order.validator.SignUpRequestValidator;
import com.nubiform.order.vo.request.SignInRequest;
import com.nubiform.order.vo.request.SignUpRequest;
import com.nubiform.order.vo.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(AuthController.API_V1_AUTH_URI)
public class AuthController {

    public static final String API_V1_AUTH_URI = "/api/v1/auth";
    public static final String SIGN_UP = "/sign-up";
    public static final String SIGN_IN = "/sign-in";
    public static final String SIGN_OUT = "/sign-out";

    private final AuthService authService;

    private final SignUpRequestValidator signUpRequestValidator;

    @InitBinder("signUpRequest")
    public void signUpRequestInitBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpRequestValidator);
    }

    @PostMapping(SIGN_UP)
    public ResponseEntity<MemberResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest, BindingResult bindingResult) {
        log.debug("signIn: {}", signUpRequest);
        if (bindingResult.hasErrors()) {
            throw ApiParameterException.of(bindingResult);
        }
        return ResponseEntity.ok(authService.signUp(signUpRequest));
    }

    @PostMapping(SIGN_IN)
    public ResponseEntity<String> signIn(@RequestBody SignInRequest signInRequest) {
        log.debug("signIn: {}", signInRequest);
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }

    @PostMapping(SIGN_OUT)
    public ResponseEntity signOut() {
        return ResponseEntity.ok().build();
    }
}
