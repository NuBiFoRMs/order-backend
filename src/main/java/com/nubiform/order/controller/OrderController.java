package com.nubiform.order.controller;

import com.nubiform.order.service.OrderService;
import com.nubiform.order.vo.request.OrderRequest;
import com.nubiform.order.vo.response.MemberOrderResponse;
import com.nubiform.order.vo.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(OrderController.API_V1_ORDERS_URI)
public class OrderController {

    public static final String API_V1_ORDERS_URI = "/api/v1/orders";
    public static final String PATH_VARIABLE_USER_ID = "/{userid}";

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<MemberOrderResponse>> getOrders(@ParameterObject Pageable pageable, @RequestParam(required = false) String username, @RequestParam(required = false) String email) {
        log.debug("getOrders: {} {} {}", pageable, username, email);
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping(PATH_VARIABLE_USER_ID)
    public ResponseEntity<List<OrderResponse>> getOrder(@PathVariable String userid) {
        log.debug("getOrder: {}", userid);
        return ResponseEntity.ok(orderService.getOrder(userid));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> order(OrderRequest orderRequest) {
        log.debug("getOrder: {}", orderRequest);
        return ResponseEntity.ok(new OrderResponse());
    }
}
