package com.abhinav3254.authservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    private ResponseEntity<?> handleCustomException(CustomException e) {
        ExceptionMessage exceptionMessage = new ExceptionMessage(e.getMessage(),e.getHttpStatus());
        return ResponseEntity.status(e.getHttpStatus()).body(exceptionMessage);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<?> handleException(Exception e) {
        ExceptionMessage exceptionMessage = new ExceptionMessage(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(exceptionMessage.getHttpStatus()).body(exceptionMessage);
    }

}
