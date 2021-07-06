package com.nubiform.order.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SignUpRequest {

    @NotBlank
    @Size(max = 20)
    @Pattern(regexp = "^[a-zA-Z가-힣]+$")
    private String username;

    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "^[a-z]+${8,}")
    private String nickname;

    @NotBlank
    @Size(max = 255)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{10,}")
    private String password;

    @NotBlank
    @Size(max = 20)
    @Pattern(regexp = "^[0-9]+$")
    private String phone;

    @NotBlank
    @Size(max = 100)
    @Email
    private String email;

    @Size(max = 20)
    private String gender;
}
