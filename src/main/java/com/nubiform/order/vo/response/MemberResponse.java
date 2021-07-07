package com.nubiform.order.vo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class MemberResponse {

    private String username;

    private String nickname;

    private String phone;

    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String gender;
}
