package com.nubiform.order.vo.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.nubiform.order.constant.ApiError;
import lombok.Data;

@Data
@JsonPropertyOrder({"code", "description", "details"})
public class ErrorResponse<T> {

    @JsonIgnore
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

    public int getCode() {
        return apiError.getCode();
    }

    public String getDescription() {
        return apiError.getDescription();
    }
}
