package com.kdu.springboothandson3.exceptions;

import com.kdu.springboothandson3.dto.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(value = {ResourceNotFound.class})
    public ResponseEntity<ErrorDto> handleCustomException(ResourceNotFound ex)
    {
        logger.info("custom exception called Resource out of bound");
        ErrorDto error=new ErrorDto(ex.getMessage()+"[  Resource not found] ", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {BadRequestCustomException.class})
    public ResponseEntity<ErrorDto> HandleBadResourceException(BadRequestCustomException ex)
    {
        logger.info("custom exception called Bad Request Exceptiom");
        ErrorDto error=new ErrorDto(ex.getMessage()+" Custom Bad Request Exception", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }




}
