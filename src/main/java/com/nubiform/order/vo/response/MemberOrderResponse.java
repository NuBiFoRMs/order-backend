package com.nubiform.order.vo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberOrderResponse {

    private MemberResponse member;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OrderResponse lastOrder;
}
