package com.nubiform.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nubiform.order.domain.Member;
import com.nubiform.order.repository.MemberRepository;
import com.nubiform.order.vo.request.SignUpRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.nubiform.order.controller.AuthController.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void signUpTest() throws Exception {
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .username("username")
                .nickname("nickname")
                .password("Password1!")
                .phone("123456789")
                .email("email@email.com")
                .gender("gender")
                .build();

        mockMvc.perform(post(API_V1_AUTH_URI + SIGN_UP)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest)))
                .andExpect(status().isOk());

        Member member = memberRepository.findByEmail(signUpRequest.getEmail()).orElse(null);

        assertThat(member).isNotNull();
    }

    @Test
    void signInTest() throws Exception {
        mockMvc.perform(post(API_V1_AUTH_URI + SIGN_IN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void signOutTest() throws Exception {
        mockMvc.perform(post(API_V1_AUTH_URI + SIGN_OUT)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}