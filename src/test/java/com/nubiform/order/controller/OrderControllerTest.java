package com.nubiform.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nubiform.order.domain.Member;
import com.nubiform.order.domain.Order;
import com.nubiform.order.repository.MemberRepository;
import com.nubiform.order.repository.OrderRepository;
import com.nubiform.order.vo.request.OrderRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static com.nubiform.order.controller.OrderController.API_V1_ORDERS_URI;
import static com.nubiform.order.controller.OrderController.PATH_VARIABLE_USER_ID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ObjectMapper objectMapper;

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
    }

    @AfterEach
    void tearDown() {
        orderRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    public void getOrderByNicknameTest() throws Exception {
        mockMvc.perform(get(API_V1_ORDERS_URI + PATH_VARIABLE_USER_ID, "nickname")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getOrderByEmailTest() throws Exception {
        mockMvc.perform(get(API_V1_ORDERS_URI + PATH_VARIABLE_USER_ID, "email")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void orderByNicknameTest() throws Exception {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setProduct("newProduct");

        mockMvc.perform(post(API_V1_ORDERS_URI + PATH_VARIABLE_USER_ID, "nickname")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderRequest)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void orderByEmailTest() throws Exception {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setProduct("newProduct");

        mockMvc.perform(post(API_V1_ORDERS_URI + PATH_VARIABLE_USER_ID, "email")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderRequest)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}