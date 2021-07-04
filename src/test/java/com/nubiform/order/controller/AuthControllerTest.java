package com.nubiform.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nubiform.order.domain.Member;
import com.nubiform.order.repository.MemberRepository;
import com.nubiform.order.vo.request.SignInRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static com.nubiform.order.controller.AuthController.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        Member member = Member.builder()
                .username("username")
                .nickname("nickname")
                .password(passwordEncoder.encode("password"))
                .phone("phone")
                .email("email")
                .gender("gender")
                .build();
        memberRepository.save(member);
    }

    @AfterEach
    void tearDown() {
        memberRepository.deleteAll();
    }

    @Test
    void signInByNicknameTest() throws Exception {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUserid("nickname");
        signInRequest.setPassword("password");

        mockMvc.perform(post(API_V1_AUTH_URI + SIGN_IN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signInRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void signInByEmailTest() throws Exception {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUserid("email");
        signInRequest.setPassword("password");

        mockMvc.perform(post(API_V1_AUTH_URI + SIGN_IN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signInRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void signInInvalidTest() throws Exception {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUserid("test");
        signInRequest.setPassword("test");

        mockMvc.perform(post(API_V1_AUTH_URI + SIGN_IN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signInRequest)))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void signOutTest() throws Exception {
        mockMvc.perform(post(API_V1_AUTH_URI + SIGN_OUT)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}