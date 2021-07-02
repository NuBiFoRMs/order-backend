package com.nubiform.order.exception;

import com.nubiform.order.constant.ApiError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ApiParameterException extends ApiException {

    private List<ErrorInfo> fields;

    public ApiParameterException(BindingResult bindingResult) {
        super(ApiError.INVALID_PARAMETER);
        this.fields = bindingResult.getAllErrors().stream()
                .map(error -> new ErrorInfo(error.getCode(), error.getObjectName(), error.getDefaultMessage()))
                .collect(Collectors.toList());
    }

    @AllArgsConstructor
    @Data
    public class ErrorInfo {
        private String code;
        private String field;
        private String message;
    }
}
