package com.nubiform.order.vo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class OrderRequest {

    @NotBlank
    @Size(max = 100, message = "{product.size}")
    private String product;
}
