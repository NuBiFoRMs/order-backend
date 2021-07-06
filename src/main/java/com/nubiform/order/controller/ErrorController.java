package com.nubiform.order.controller;

import com.nubiform.order.constant.ApiError;
import com.nubiform.order.exception.ApiException;
import com.nubiform.order.exception.ApiParameterException;
import com.nubiform.order.vo.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> apiException(ApiException e) {
        log.error("apiException: {}", e.getLocalizedMessage());
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(e.getApiError(), e.getLocalizedMessage()));
    }

    @ExceptionHandler(ApiParameterException.class)
    public ResponseEntity<ErrorResponse> apiParameterException(ApiParameterException e) {
        log.error("apiParameterException: {}", e.getLocalizedMessage());
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(e.getApiError(), e.getLocalizedMessage(), e.getFields()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> badRequest(HttpMessageNotReadableException e) {
        log.error("badRequest: {}", e.getLocalizedMessage());
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.of(ApiError.INVALID_PARAMETER));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> badCredentialsException(BadCredentialsException e) {
        log.error("badCredentialsException: {}", e.getLocalizedMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(ApiError.UNAUTHORIZED, e.getLocalizedMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception e) {
        log.error("exception: {}, {}", e.getClass(), e.getLocalizedMessage());
        return ResponseEntity
                .internalServerError()
                .body(ErrorResponse.of(ApiError.ERROR));
    }
}
