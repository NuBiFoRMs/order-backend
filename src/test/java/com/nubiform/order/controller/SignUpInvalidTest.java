package com.nubiform.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nubiform.order.constant.ApiError;
import com.nubiform.order.repository.MemberRepository;
import com.nubiform.order.vo.request.SignUpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static com.nubiform.order.controller.AuthController.API_V1_AUTH_URI;
import static com.nubiform.order.controller.AuthController.SIGN_UP;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class SignUpInvalidTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ObjectMapper objectMapper;

    SignUpRequest signUpRequest;

    @BeforeEach
    void setUp() {
        signUpRequest = SignUpRequest.builder()
                .username("username")
                .nickname("nickname")
                .password("Password1!")
                .phone("123456789")
                .email("email@email.com")
                .gender("gender")
                .build();
    }

    @Test
    void invalidUsernameTest() throws Exception {
        signUpRequest.setUsername("12345!@#$%");

        mockMvc.perform(post(API_V1_AUTH_URI + SIGN_UP)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(ApiError.INVALID_PARAMETER.getCode()))
                .andExpect(jsonPath("$.description").value(ApiError.INVALID_PARAMETER.getDescription()))
                .andExpect(jsonPath("$.details[0].field").value("username"));
    }

    @Test
    void invalidNicknameTest() throws Exception {
        signUpRequest.setNickname("12345!@#$%");

        mockMvc.perform(post(API_V1_AUTH_URI + SIGN_UP)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(ApiError.INVALID_PARAMETER.getCode()))
                .andExpect(jsonPath("$.description").value(ApiError.INVALID_PARAMETER.getDescription()))
                .andExpect(jsonPath("$.details[0].field").value("nickname"));
    }

    @Test
    void invalidPasswordTest() throws Exception {
        signUpRequest.setPassword("password");

        mockMvc.perform(post(API_V1_AUTH_URI + SIGN_UP)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(ApiError.INVALID_PARAMETER.getCode()))
                .andExpect(jsonPath("$.description").value(ApiError.INVALID_PARAMETER.getDescription()))
                .andExpect(jsonPath("$.details[0].field").value("password"));
    }

    @Test
    void invalidPhoneTest() throws Exception {
        signUpRequest.setPhone("phone");

        mockMvc.perform(post(API_V1_AUTH_URI + SIGN_UP)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(ApiError.INVALID_PARAMETER.getCode()))
                .andExpect(jsonPath("$.description").value(ApiError.INVALID_PARAMETER.getDescription()))
                .andExpect(jsonPath("$.details[0].field").value("phone"));
    }

    @Test
    void invalidEmailTest() throws Exception {
        signUpRequest.setEmail("email");

        mockMvc.perform(post(API_V1_AUTH_URI + SIGN_UP)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(ApiError.INVALID_PARAMETER.getCode()))
                .andExpect(jsonPath("$.description").value(ApiError.INVALID_PARAMETER.getDescription()))
                .andExpect(jsonPath("$.details[0].field").value("email"));
    }

    @Test
    void invalidAllTest() throws Exception {
        signUpRequest.setUsername("12345!@#$%");
        signUpRequest.setNickname("12345!@#$%");
        signUpRequest.setPassword("password");
        signUpRequest.setPhone("phone");
        signUpRequest.setEmail("email");

        mockMvc.perform(post(API_V1_AUTH_URI + SIGN_UP)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(ApiError.INVALID_PARAMETER.getCode()))
                .andExpect(jsonPath("$.description").value(ApiError.INVALID_PARAMETER.getDescription()))
                .andExpect(jsonPath("$.details.length()").value(5));
    }
}