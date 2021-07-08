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
    @Size(max = 20, message = "{username.size}")
    @Pattern(regexp = "^[a-zA-Z가-힣]+$", message = "{username.pattern}")
    private String username;

    @NotBlank
    @Size(max = 30, message = "{nickname.size}")
    @Pattern(regexp = "^[a-z]+${8,}", message = "{nickname.pattern}")
    private String nickname;

    @NotBlank
    @Size(max = 255, message = "{password.size}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{10,}", message = "{password.pattern}")
    private String password;

    @NotBlank
    @Size(max = 20, message = "{phone.size}")
    @Pattern(regexp = "^[0-9]+$", message = "{phone.pattern}")
    private String phone;

    @NotBlank
    @Size(max = 100, message = "{email.size}")
    @Email(message = "{email.pattern}")
    private String email;

    @Size(max = 20, message = "{gender.size}")
    private String gender;
}
