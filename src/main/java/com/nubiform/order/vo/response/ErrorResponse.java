package com.nubiform.order.vo.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.nubiform.order.constant.ApiError;
import lombok.Data;

@Data
@JsonPropertyOrder({"code", "description", "details"})
public class ErrorResponse {

    @JsonIgnore
    private ApiError apiError;

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
        this.apiError = apiError;
        this.message = message;
        this.details = details;
    }

    public static ErrorResponse of(ApiError apiError) {
        return new ErrorResponse(apiError);
    }

    public int getCode() {
        return apiError.getCode();
    }

    public String getDescription() {
        return apiError.getDescription();
    }
}
