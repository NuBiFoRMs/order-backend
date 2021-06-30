package com.nubiform.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @GetMapping("/orders")
    public void getOrders(@ParameterObject Pageable pageable) {
        log.debug("getOrders: {}", pageable);
    }

    @GetMapping("/orders/{id}")
    public void getOrder(@PathVariable int id) {
        log.debug("getOrder: {}", id);
    }

    @PostMapping("/orders/")
    public void resistOrder() {

    }
}
