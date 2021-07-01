package com.nubiform.order.controller;

import com.nubiform.order.vo.request.SignInRequest;
import com.nubiform.order.vo.request.SignUpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(AuthController.API_V1_AUTH_URI)
public class AuthController {

    public static final String API_V1_AUTH_URI = "/api/v1/auth";
    public static final String SIGN_UP = "/sign-up";
    public static final String SIGN_IN = "/sign-in";
    public static final String SIGN_OUT = "/sign-out";

    @PostMapping(SIGN_UP)
    public ResponseEntity signUp(@Valid SignUpRequest signUpRequest) {
        log.debug("signIn: {}", signUpRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping(SIGN_IN)
    public ResponseEntity signIn(SignInRequest signInRequest) {
        log.debug("signIn: {}", signInRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping(SIGN_OUT)
    public ResponseEntity signOut() {
        return ResponseEntity.ok().build();
    }
}
