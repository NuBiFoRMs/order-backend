package com.nubiform.order.vo.request;

import lombok.Data;

@Data
public class SignUpRequest {

    private String username;

    private String nickname;

    private String password;

    private String phone;

    private String email;

    private String gender;
}
