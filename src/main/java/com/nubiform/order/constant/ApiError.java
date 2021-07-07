package com.nubiform.order.constant;

import lombok.Getter;

@Getter
public enum ApiError {

    ERROR(1000, "error"),
    INVALID_PARAMETER(1010, "invalid parameter"),
    NO_DATA_FOUND(1020, "no data found"),
    MEMBER_NOT_FOUND(1021, "member not found"),
    ORDER_NOT_FOUND(1022, "order not found"),
    UNAUTHORIZED(1030, "unauthorized"),
    FORBIDDEN(1040, "forbidden");

    private final int code;

    private final String description;

    ApiError(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
