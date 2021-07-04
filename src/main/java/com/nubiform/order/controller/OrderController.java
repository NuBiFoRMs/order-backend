package com.nubiform.order.controller;

import com.nubiform.order.service.OrderService;
import com.nubiform.order.vo.request.OrderRequest;
import com.nubiform.order.vo.response.OrderResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.nubiform.order.config.security.jwt.JwtConstant.AUTHORIZATION_HEADER;

@SecurityRequirement(name = AUTHORIZATION_HEADER)
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(OrderController.API_V1_ORDERS_URI)
public class OrderController {

    public static final String API_V1_ORDERS_URI = "/api/v1/orders";
    public static final String PATH_VARIABLE_USER_ID = "/{userid}";

    private final OrderService orderService;

    @GetMapping(PATH_VARIABLE_USER_ID)
    public ResponseEntity<List<OrderResponse>> getOrder(@PathVariable String userid) {
        log.debug("getOrder: {}", userid);
        return ResponseEntity.ok(orderService.getOrder(userid));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> order(@AuthenticationPrincipal User user, @RequestBody OrderRequest orderRequest) {
        log.debug("order: {} {}", user, orderRequest);
        return ResponseEntity.ok(orderService.order(user.getUsername(), orderRequest));
    }
}
