package com.nubiform.order.controller;

import com.nubiform.order.vo.response.MemberResponse;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class MemberController {

    @GetMapping("/members")
    public ResponseEntity<List<MemberResponse>> getMembers(@ParameterObject Pageable pageable) {
        log.debug("getOrders: {} {} {}", pageable);
        return ResponseEntity.ok(new ArrayList<MemberResponse>());
    }

    @GetMapping("/members/{userid}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable String userid) {
        log.debug("getMember: {}", userid);
        return ResponseEntity.ok(new MemberResponse());
    }
}
