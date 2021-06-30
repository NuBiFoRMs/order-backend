package com.nubiform.order.controller;

import com.nubiform.order.vo.request.SignInRequest;
import com.nubiform.order.vo.request.SignUpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @PostMapping("/sign-up")
    public ResponseEntity signUp(SignUpRequest signUpRequest) {
        log.debug("signIn: {}", signUpRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity signIn(SignInRequest signInRequest) {
        log.debug("signIn: {}", signInRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-out")
    public ResponseEntity signOut() {
        return ResponseEntity.ok().build();
    }
}
