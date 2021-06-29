package com.nubiform.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class MemberController {

    @GetMapping("/members")
    public void getMembers() {

    }

    @GetMapping("/members/{id}")
    public void getMember(@PathVariable int id) {
        log.debug("getMember: {}", id);
    }
}
