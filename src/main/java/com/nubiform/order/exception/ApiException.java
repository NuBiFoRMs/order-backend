package com.nubiform.order.exception;

import com.nubiform.order.constant.ApiError;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private final ApiError apiError;

    public ApiException(ApiError apiError) {
        this(apiError, null);
    }

    public ApiException(ApiError apiError, String message) {
        super(message);
        this.apiError = apiError;
    }

    public static ApiException of(ApiError apiError) {
        return new ApiException(apiError);
    }
}
