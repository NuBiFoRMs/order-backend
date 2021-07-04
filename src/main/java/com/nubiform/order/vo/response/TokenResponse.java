package com.nubiform.order.vo.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TokenResponse {

    private String token;
}
