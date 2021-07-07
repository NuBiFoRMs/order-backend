package com.nubiform.order.controller;

import com.nubiform.order.vo.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class ErrorHandlingController implements ErrorController {

    public static final String ERROR_URI = "/error";

    @Operation(hidden = true)
    @RequestMapping(ERROR_URI)
    public ResponseEntity<ErrorResponse> errorHandling(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
        log.error("errorHandling: {}", httpStatus);
        return ResponseEntity.status(httpStatus).body(ErrorResponse.of(httpStatus));
    }

}
