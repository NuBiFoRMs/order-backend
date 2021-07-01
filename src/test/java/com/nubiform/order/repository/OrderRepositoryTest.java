package com.nubiform.order.repository;

import com.nubiform.order.domain.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void orderSaveTest() {
        Order order = Order.builder()
                .product("product")
                .orderDate(LocalDateTime.now())
                .build();
        orderRepository.save(order);

        Order newOrder = orderRepository.findById(order.getId()).orElse(null);

        assertNotNull(newOrder);
        assertEquals(order.getProduct(), newOrder.getProduct());
    }
}