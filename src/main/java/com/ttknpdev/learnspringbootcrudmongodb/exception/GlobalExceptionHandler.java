package com.ttknpdev.learnspringbootcrudmongodb.exception;

import com.ttknpdev.learnspringbootcrudmongodb.exception.entity.Error;
import com.ttknpdev.learnspringbootcrudmongodb.exception.handler.ResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    /*
    For calling my exception
    but this case I don't need it
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<Error> errorResponseResourceNotFound (ResourceNotFound resourceNotFound , WebRequest webRequest) {
        Error error = new Error(new Date(),resourceNotFound.getMessage(),webRequest.getDescription(false));
        // webRequest.getDescription(false) it returns Url that found error As. uri=/api/em/create
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    */
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<Error> errorResponseResourceNotFound (ResourceNotFound resourceNotFound , WebRequest webRequest) {
        Error error = new Error(
                new Date(),
                resourceNotFound.getMessage(),
                webRequest.getDescription(false)
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> errorResponseResourceNotFound (Exception exception , WebRequest webRequest) {
        Error error = new Error(
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}
