package com.example.CTDT_APP.exception;

import com.example.CTDT_APP.dto.response.ApiRespone;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiRespone> handlingRuntimeException(AppException exception){
        ApiRespone apiRespone= new ApiRespone();
        ErrorCode errorCode= exception.getErrorCode();
        apiRespone.setCode(errorCode.getCode());
        apiRespone.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiRespone);
    }

}
