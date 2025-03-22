package com.example.CTDT_APP.exception;

import com.example.CTDT_APP.dto.response.ApiResponse;
import com.example.CTDT_APP.dto.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ErrorResponse> handlingRuntimeException(AppException exception) {
        ErrorResponse response = ErrorResponse.builder()
                .code(400)
                .message(exception.getMessage())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();
        ErrorResponse response = ErrorResponse.builder()
                .code(400)
                .message(errors)
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    ResponseEntity<ErrorResponse> handlingException(Exception exception) {
        ErrorResponse response = ErrorResponse.builder()
                .code(500)
                .message("Internal server error")
                .build();
        exception.printStackTrace();
        return ResponseEntity.internalServerError().body(response);
    }
}
