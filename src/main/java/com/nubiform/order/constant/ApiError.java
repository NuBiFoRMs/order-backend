package com.nubiform.order.constant;

import lombok.Getter;

@Getter
public enum ApiError {

    ERROR(1000, "error"),
    INVALID_PARAMETER(1001, "invalid parameter"),
    NO_DATA_FOUND(1002, "no data found"),
    UNAUTHORIZED(1003, "unauthorized"),
    FORBIDDEN(1004, "forbidden");

    private final int code;

    private final String description;

    ApiError(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
