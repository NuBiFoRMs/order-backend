package com.nubiform.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @PostMapping("/sign-up")
    public void signUp() {

    }

    @PostMapping("/sign-in")
    public void signIn() {

    }

    @PostMapping("/sign-out")
    public void signOut() {

    }
}
