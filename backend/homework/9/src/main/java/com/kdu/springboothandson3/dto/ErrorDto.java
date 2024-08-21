package com.kdu.springboothandson3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ErrorDto {
    String message;
    int statusCode;
}
