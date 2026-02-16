package com.cck.cck_app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({
            UserNotFoundException.class,
            AlreadyExists.class,
            IllegalArgumentException.class,
            UsernameNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleUserNotFound(Exception ex) {

        HttpStatus status = switch (ex) {
            case UserNotFoundException userNotFoundException -> HttpStatus.NOT_FOUND;          // 404

            case AlreadyExists alreadyExists -> HttpStatus.CONFLICT;          // 409

            case IllegalArgumentException illegalArgumentException -> HttpStatus.BAD_REQUEST;       // 400

            case null, default -> HttpStatus.INTERNAL_SERVER_ERROR; // 500

        };

//        assert ex != null;
        ErrorResponse error = new ErrorResponse(
                status.value(),
                ex != null ? ex.getMessage() : "null"
        );

        return new ResponseEntity<>(error, status);
    }
}
