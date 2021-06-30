package com.nubiform.order.vo.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponse {

    private String id;

    private String product;

    private LocalDateTime orderDate;
}
