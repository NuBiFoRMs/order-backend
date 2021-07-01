package com.nubiform.order.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.nubiform.order.controller.OrderController.API_V1_ORDERS_URI;
import static com.nubiform.order.controller.OrderController.PATH_VARIABLE_USER_ID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getOrdersTest() throws Exception {
        mockMvc.perform(get("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getOrdersPageTest() throws Exception {
        mockMvc.perform(get(API_V1_ORDERS_URI)
                .param("page", "0")
                .param("size", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getOrdersPageAndParamsTest() throws Exception {
        mockMvc.perform(get(API_V1_ORDERS_URI)
                .param("page", "0")
                .param("size", "10")
                .param("username", "username")
                .param("email", "email")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getOrderTest() throws Exception {
        mockMvc.perform(get(API_V1_ORDERS_URI + PATH_VARIABLE_USER_ID, 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void orderTest() throws Exception {
        mockMvc.perform(post(API_V1_ORDERS_URI)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}