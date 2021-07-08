package com.nubiform.order.controller;

import com.nubiform.order.exception.ApiParameterException;
import com.nubiform.order.service.OrderService;
import com.nubiform.order.vo.request.OrderRequest;
import com.nubiform.order.vo.response.OrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.nubiform.order.config.security.jwt.JwtConstant.AUTHORIZATION_HEADER;

@Tag(name = "Order", description = "Order API")
@SecurityRequirement(name = AUTHORIZATION_HEADER)
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(OrderController.API_V1_ORDERS_URI)
public class OrderController {

    public static final String API_V1_ORDERS_URI = "/api/v1/orders";
    public static final String PATH_VARIABLE_USER_ID = "/{userid}";

    private final OrderService orderService;

    @Operation(summary = "단일회원주문정보", description = "단일 회원 주문 정보조회를 수행합니다.")
    @GetMapping(PATH_VARIABLE_USER_ID)
    public ResponseEntity<List<OrderResponse>> getOrder(@Parameter(description = "nickname or email") @PathVariable String userid) {
        log.debug("getOrder: {}", userid);
        return ResponseEntity.ok(orderService.getOrder(userid));
    }

    @Operation(summary = "주문생성", description = "주문을 생성합니다.")
    @PostMapping
    public ResponseEntity<OrderResponse> order(@AuthenticationPrincipal User user, @Valid @RequestBody OrderRequest orderRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw ApiParameterException.of(bindingResult);
        }
        log.debug("order: {} {}", user, orderRequest);
        return ResponseEntity.ok(orderService.order(user.getUsername(), orderRequest));
    }
}
