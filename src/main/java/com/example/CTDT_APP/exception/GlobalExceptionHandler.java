package com.example.CTDT_APP.exception;

import com.example.CTDT_APP.dto.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ErrorResponse> handlingRuntimeException(AppException exception) {
        ErrorResponse response = ErrorResponse.builder()
                .code(400)
                .message(exception.getMessage())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ErrorResponse> handlingException(Exception exception) {
        ErrorResponse response = ErrorResponse.builder()
                .code(500)
                .message("Internal server error")
                .build();
        exception.printStackTrace();
        return ResponseEntity.internalServerError().body(response);
    }
}
