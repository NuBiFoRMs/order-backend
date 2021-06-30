package com.nubiform.order.vo.response;

import lombok.Data;

@Data
public class MemberOrderResponse {

    MemberResponse member;

    OrderResponse order;
}
