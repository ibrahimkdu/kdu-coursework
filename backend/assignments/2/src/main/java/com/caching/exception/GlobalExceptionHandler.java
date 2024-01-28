package com.caching.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({ApiNullResponse.class, InvalidRequestException.class, GeoCodingApiException.class, ReverseGeoCodingApiException.class})
    public ResponseEntity<Object> handleApiNullResponse(RuntimeException apiNullResponse) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiNullResponse.getMessage());
    }
}