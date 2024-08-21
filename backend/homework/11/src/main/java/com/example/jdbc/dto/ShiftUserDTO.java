package com.example.jdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiftUserDTO {
    private UUID id;
    private UUID shiftId;
    private UUID userId;
    private UUID tenantId;

}
