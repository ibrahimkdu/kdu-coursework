package com.kdu.smarthome.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestDeviceRegisterDTO {
    String kickston_id;
    String device_username;
    String device_password;
}
