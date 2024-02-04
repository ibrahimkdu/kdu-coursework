package com.kdu.smartHome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceRequestDTO {

    private String kickstonId;
    private String deviceUsername;
    private String devicePassword;

    // Constructors, getters, setters
}