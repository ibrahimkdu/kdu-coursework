package com.kdu.smarthome.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestAddDeviceDTO {

    private String houseId;
    private String roomId;
    private String kickstonId;
}
