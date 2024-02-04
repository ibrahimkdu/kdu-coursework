package com.kdu.smartHome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private String message;
    private String userId;  // Change the type accordingly
    private int httpStatus;

    // Constructors, getters, setters
}
