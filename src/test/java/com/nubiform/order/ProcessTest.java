package com.nubiform.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nubiform.order.repository.MemberRepository;
import com.nubiform.order.repository.OrderRepository;
import com.nubiform.order.vo.request.SignInRequest;
import com.nubiform.order.vo.request.SignUpRequest;
import com.nubiform.order.vo.response.MemberResponse;
import com.nubiform.order.vo.response.TokenResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static com.nubiform.order.config.security.jwt.JwtConstant.AUTHORIZATION_HEADER;
import static com.nubiform.order.config.security.jwt.JwtConstant.BEARER;
import static com.nubiform.order.controller.AuthController.*;
import static com.nubiform.order.controller.MemberController.API_V1_MEMBERS_URI;
import static com.nubiform.order.controller.MemberController.PATH_VARIABLE_USER_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class ProcessTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ObjectMapper objectMapper;

    @AfterEach
    void tearDown() {
        orderRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입,로그인,단일회원정보조회")
    void process1() throws Exception {
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .username("username")
                .nickname("nickname")
                .password("Password1!")
                .phone("123456789")
                .email("email@email.com")
                .gender("gender")
                .build();

        // 회원가입
        signUp(signUpRequest);

        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUserid(signUpRequest.getNickname());
        signInRequest.setPassword(signUpRequest.getPassword());

        // 로그인
        TokenResponse tokenResponse = signIn(signInRequest);

        // 단일회원정보조회
        MemberResponse memberResponse = member(tokenResponse.getToken(), signInRequest.getUserid());

        assertThat(memberResponse)
                .isNotNull()
                .usingRecursiveComparison().ignoringFields("password").isEqualTo(signUpRequest);
    }

    MemberResponse signUp(SignUpRequest signUpRequest) throws Exception {
        String response = mockMvc.perform(post(API_V1_AUTH_URI + SIGN_UP)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(response, MemberResponse.class);
    }

    TokenResponse signIn(SignInRequest signInRequest) throws Exception {
        String response = mockMvc.perform(post(API_V1_AUTH_URI + SIGN_IN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signInRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(response, TokenResponse.class);
    }

    MemberResponse member(String token, String userid) throws Exception {
        String response = mockMvc.perform(get(API_V1_MEMBERS_URI + PATH_VARIABLE_USER_ID, userid)
                .header(AUTHORIZATION_HEADER, String.join(" ", BEARER, token))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(response, MemberResponse.class);
    }
}