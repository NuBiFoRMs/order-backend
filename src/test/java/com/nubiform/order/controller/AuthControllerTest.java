package com.nubiform.order.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void signUpTest() throws Exception {
        mockMvc.perform(post("/api/v1/sign-up")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void signInTest() throws Exception {
        mockMvc.perform(post("/api/v1/sign-in")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void signOutTest() throws Exception {
        mockMvc.perform(post("/api/v1/sign-out")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}