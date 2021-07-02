package com.nubiform.order.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SignUpRequest {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z가-힣]+$")
    private String username;

    @NotBlank
    @Pattern(regexp = "^[a-z]+$")
    private String nickname;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{10,}")
    private String password;

    @NotBlank
    @Pattern(regexp = "^[0-9]+$")
    private String phone;

    @NotBlank
    @Email
    private String email;

    private String gender;
}
