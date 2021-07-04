package com.nubiform.order.vo.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponse {

    private Long id;

    private String product;

    private LocalDateTime orderDate;
}
