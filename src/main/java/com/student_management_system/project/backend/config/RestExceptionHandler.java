package com.student_management_system.project.backend.config;

import com.student_management_system.project.backend.exceptions.AppException;

import jakarta.security.auth.message.AuthException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<String> handleException(AppException e) {

        String responseMessage = e.getMessage();
        return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = AuthException.class)
    public ResponseEntity<String> exception(AuthException e) {

        String responseMessage = e.getMessage();
        return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> exception(MethodArgumentNotValidException e) {

        String responseMessage = e.getMessage();
        return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<String> exception(RuntimeException e) {

        String responseMessage = e.getMessage();
        return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}