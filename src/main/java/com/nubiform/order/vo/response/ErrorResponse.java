package com.nubiform.order.vo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nubiform.order.constant.ApiError;
import lombok.Data;

@Data
public class ErrorResponse<T> {

    private ApiError apiError;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T details;

    public ErrorResponse(ApiError apiError, String message) {
        this(apiError, message, null);
    }
    
    public ErrorResponse(ApiError apiError, String message, T details) {
        this.apiError = apiError;
        this.message = message;
        this.details = details;
    }
}
