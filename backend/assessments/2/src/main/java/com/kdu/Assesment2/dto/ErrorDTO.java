package com.example.jpahw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorDTO {
        private String message;
        private HttpStatus httpStatus;
}
