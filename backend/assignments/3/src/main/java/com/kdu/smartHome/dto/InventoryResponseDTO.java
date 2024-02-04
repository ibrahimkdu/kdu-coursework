package com.kdu.smartHome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InventoryResponseDTO {
    private String message;
    private String objectId;
    private Integer httpStatus;
}