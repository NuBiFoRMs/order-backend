package com.nubiform.order.exception;

import com.nubiform.order.constant.ApiError;

public class ApiNotFoundException extends ApiException {

    public ApiNotFoundException(ApiError apiError) {
        super(apiError);
    }

    public ApiNotFoundException(ApiError apiError, String message) {
        super(apiError, message);
    }

    public static ApiNotFoundException of(ApiError apiError) {
        return new ApiNotFoundException(apiError);
    }
}
