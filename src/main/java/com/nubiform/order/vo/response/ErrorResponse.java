package com.nubiform.order.vo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.nubiform.order.constant.ApiError;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@JsonPropertyOrder({"code", "description", "details"})
public class ErrorResponse {

    private int code;

    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object details;

    public ErrorResponse(ApiError apiError) {
        this(apiError, null, null);
    }

    public ErrorResponse(ApiError apiError, String message) {
        this(apiError, message, null);
    }

    public ErrorResponse(ApiError apiError, String message, Object details) {
        this.code = apiError.getCode();
        this.description = apiError.getDescription();
        this.message = message;
        this.details = details;
    }

    public ErrorResponse(HttpStatus httpStatus) {
        this.code = httpStatus.value();
        this.description = httpStatus.getReasonPhrase();
    }

    public static ErrorResponse of(ApiError apiError) {
        return new ErrorResponse(apiError);
    }

    public static ErrorResponse of(HttpStatus httpStatus) {
        return new ErrorResponse(httpStatus);
    }
}
