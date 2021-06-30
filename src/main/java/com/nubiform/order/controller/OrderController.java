package com.nubiform.order.controller;

import com.nubiform.order.vo.request.OrderRequest;
import com.nubiform.order.vo.response.MemberOrderResponse;
import com.nubiform.order.vo.response.OrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @GetMapping
    public ResponseEntity<List<MemberOrderResponse>> getOrders(@ParameterObject Pageable pageable, String username, String email) {
        log.debug("getOrders: {} {} {}", pageable, username, email);
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/{userid}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable String userid) {
        log.debug("getOrder: {}", userid);
        return ResponseEntity.ok(new OrderResponse());
    }

    @PostMapping
    public ResponseEntity<OrderResponse> order(OrderRequest orderRequest) {
        log.debug("getOrder: {}", orderRequest);
        return ResponseEntity.ok(new OrderResponse());
    }
}
