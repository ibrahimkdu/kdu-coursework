package com.kdu.smartHome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceDTO {
    private String kickstoneId;
    private String name;
    private String password;
}
