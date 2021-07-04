package com.nubiform.order.controller;

import com.nubiform.order.domain.Member;
import com.nubiform.order.domain.Order;
import com.nubiform.order.repository.MemberRepository;
import com.nubiform.order.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static com.nubiform.order.controller.MemberController.API_V1_MEMBERS_URI;
import static com.nubiform.order.controller.MemberController.PATH_VARIABLE_USER_ID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class MemberUnauthorizedTest {

    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_EMAIL = "email";
    public static final String PARAM_SORT = "sort";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        Member member = Member.builder()
                .username("username")
                .nickname("nickname")
                .password("password")
                .phone("phone")
                .email("email")
                .gender("gender")
                .build();
        memberRepository.save(member);

        Order order1 = Order.builder()
                .product("product1")
                .orderDate(LocalDateTime.now())
                .build();
        order1.setMember(member);
        orderRepository.save(order1);

        Order order2 = Order.builder()
                .product("product2")
                .orderDate(LocalDateTime.now())
                .build();
        order2.setMember(member);
        orderRepository.save(order2);

        Member memberX = Member.builder()
                .username("username")
                .nickname("nicknameX")
                .password("password")
                .phone("phone")
                .email("emailX")
                .gender("gender")
                .build();
        memberRepository.save(memberX);
    }

    @AfterEach
    void tearDown() {
        orderRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    public void getMemberByNicknameTest() throws Exception {
        mockMvc.perform(get(API_V1_MEMBERS_URI + PATH_VARIABLE_USER_ID, "nickname")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getMemberByEmailTest() throws Exception {
        mockMvc.perform(get(API_V1_MEMBERS_URI + PATH_VARIABLE_USER_ID, "email")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getMemberNoDataTest() throws Exception {
        mockMvc.perform(get(API_V1_MEMBERS_URI + PATH_VARIABLE_USER_ID, "noData")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getMembersTest() throws Exception {
        mockMvc.perform(get(API_V1_MEMBERS_URI)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getMembersByPageAscTest() throws Exception {
        mockMvc.perform(get(API_V1_MEMBERS_URI)
                .param(PARAM_SORT, "nickname")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getMembersByPageDescTest() throws Exception {
        mockMvc.perform(get(API_V1_MEMBERS_URI)
                .param(PARAM_SORT, "nickname,desc")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getMembersByUsernameTest() throws Exception {
        mockMvc.perform(get(API_V1_MEMBERS_URI)
                .param(PARAM_USERNAME, "username")
                .param(PARAM_SORT, "nickname")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getMembersByEmailTest() throws Exception {
        mockMvc.perform(get(API_V1_MEMBERS_URI)
                .param(PARAM_EMAIL, "email")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getMembersEmptyTest() throws Exception {
        mockMvc.perform(get(API_V1_MEMBERS_URI)
                .param(PARAM_USERNAME, "empty")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}