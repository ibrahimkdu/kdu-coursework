package com.kdu.smarthome.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RequestHouseDTO {
    String address;
    String house_name;
}
