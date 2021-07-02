package com.nubiform.order.controller;

import com.nubiform.order.service.MemberService;
import com.nubiform.order.vo.response.MemberOrderResponse;
import com.nubiform.order.vo.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(MemberController.API_V1_MEMBERS_URI)
public class MemberController {

    public static final String API_V1_MEMBERS_URI = "/api/v1/members";
    public static final String PATH_VARIABLE_USER_ID = "/{userid}";

    private final MemberService memberService;

    @GetMapping(PATH_VARIABLE_USER_ID)
    public ResponseEntity<MemberResponse> getMember(@PathVariable String userid) {
        log.debug("getMember: {}", userid);
        return ResponseEntity.ok(memberService.getMember(userid));
    }

    @GetMapping
    public ResponseEntity<Page<MemberOrderResponse>> getMembers(@ParameterObject Pageable pageable, @RequestParam(required = false) String username, @RequestParam(required = false) String email) {
        log.debug("getMembers: {}", pageable);
        return ResponseEntity.ok(memberService.getMembers(pageable, username, email));
    }
}
