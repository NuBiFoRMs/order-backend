package com.nubiform.order.controller;

import com.nubiform.order.exception.ApiException;
import com.nubiform.order.exception.ApiParameterException;
import com.nubiform.order.vo.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> apiException(ApiException e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(e.getApiError(), e.getLocalizedMessage()));
    }

    @ExceptionHandler(ApiParameterException.class)
    public ResponseEntity<ErrorResponse<List<ApiParameterException.ErrorInfo>>> apiParameterException(ApiParameterException e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse<>(e.getApiError(), e.getLocalizedMessage(), e.getFields()));
    }
}
