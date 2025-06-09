package com.example.CTDT_APP.exception;

import com.example.CTDT_APP.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({AppException.class})
    ResponseEntity<ErrorResponse> handlingRuntimeException(AppException exception) {
        ErrorResponse response = ErrorResponse.builder()
                .code(400)
                .message(exception.getMessage())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentials(BadCredentialsException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .code(401)
                .message("Mật khẩu không đúng")
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex) {
        ErrorResponse apiResponse = ErrorResponse.builder()
                .code(403)
                .message("Bạn không có quyền truy cập tài nguyên này")
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiResponse);
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

    @ExceptionHandler({JpaSystemException.class, GenericJDBCException.class})
    public ResponseEntity<ErrorResponse> handleJpaException(Exception ex) {
        Throwable root = getDeepestCause(ex);
        ErrorResponse response = ErrorResponse.builder()
                .code(400)
                .message(root.getMessage())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    private Throwable getDeepestCause(Throwable ex) {
        Throwable cause = ex;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        return cause;
    }

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    ResponseEntity<ErrorResponse> handlingException(Exception exception) {
        ErrorResponse response = ErrorResponse.builder()
                .code(500)
                .message("Internal server error")
                .build();
        log.error("Internal server error: {}", exception.getMessage(), exception);
        return ResponseEntity.internalServerError().body(response);
    }
}
