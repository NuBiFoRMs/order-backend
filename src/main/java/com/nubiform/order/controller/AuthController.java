package com.nubiform.order.controller;

import com.nubiform.order.exception.ApiParameterException;
import com.nubiform.order.service.AuthService;
import com.nubiform.order.validator.SignUpRequestValidator;
import com.nubiform.order.vo.request.SignInRequest;
import com.nubiform.order.vo.request.SignUpRequest;
import com.nubiform.order.vo.response.MemberResponse;
import com.nubiform.order.vo.response.TokenResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.nubiform.order.config.security.jwt.JwtConstant.*;

@OpenAPIDefinition(info = @Info(title = "Order Management Project", version = "v1", description = "Order Management Project"))
@Tag(name = "Authorization", description = "Authorization API")
@SecurityScheme(type = SecuritySchemeType.HTTP, scheme = BEARER, bearerFormat = JWT, name = AUTHORIZATION_HEADER, in = SecuritySchemeIn.HEADER)
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(AuthController.API_V1_AUTH_URI)
public class AuthController {

    public static final String API_V1_AUTH_URI = "/api/v1/auth";
    public static final String SIGN_UP = "/sign-up";
    public static final String SIGN_IN = "/sign-in";
    public static final String SIGN_OUT = "/sign-out";

    private final AuthService authService;

    private final SignUpRequestValidator signUpRequestValidator;

    @InitBinder("signUpRequest")
    public void signUpRequestInitBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpRequestValidator);
    }

    @Operation(summary = "회원가입", description = "회원 가입을 수행합니다.")
    @PostMapping(SIGN_UP)
    public ResponseEntity<MemberResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest, BindingResult bindingResult) {
        log.debug("signIn: {}", signUpRequest);
        if (bindingResult.hasErrors()) {
            throw ApiParameterException.of(bindingResult);
        }
        return ResponseEntity.ok(authService.signUp(signUpRequest));
    }

    @Operation(summary = "로그인", description = "회원 로그인을 수행합니다.")
    @PostMapping(SIGN_IN)
    public ResponseEntity<TokenResponse> signIn(@RequestBody SignInRequest signInRequest) {
        log.debug("signIn: {}", signInRequest);
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }

    @Operation(summary = "로그아웃", description = "회원 로그아웃을 수행합니다.")
    @PostMapping(SIGN_OUT)
    public ResponseEntity signOut() {
        return ResponseEntity.ok().build();
    }
}
