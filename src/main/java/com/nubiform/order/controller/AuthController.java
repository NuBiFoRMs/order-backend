package com.nubiform.order.controller;

import com.nubiform.order.service.AuthService;
import com.nubiform.order.vo.request.SignInRequest;
import com.nubiform.order.vo.request.SignUpRequest;
import com.nubiform.order.vo.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(AuthController.API_V1_AUTH_URI)
public class AuthController {

    private final AuthService authService;

    public static final String API_V1_AUTH_URI = "/api/v1/auth";
    public static final String SIGN_UP = "/sign-up";
    public static final String SIGN_IN = "/sign-in";
    public static final String SIGN_OUT = "/sign-out";

    @PostMapping(SIGN_UP)
    public ResponseEntity<MemberResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        log.debug("signIn: {}", signUpRequest);
        return ResponseEntity.ok(authService.signUp(signUpRequest));
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
