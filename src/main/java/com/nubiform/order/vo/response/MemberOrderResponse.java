package com.nubiform.order.vo.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberOrderResponse {

    private MemberResponse member;

    private OrderResponse lastOrder;
}
