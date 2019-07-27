package com.cric;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class CustomExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST,"Server Error",ex);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFound.class)
    public final ResponseEntity<Object> handleEntityNotFoundException(EntityNotFound ex, WebRequest request) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST,"Server Error",ex);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
}
