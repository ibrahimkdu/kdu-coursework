package com.example.jdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;


@Data
@AllArgsConstructor
public class ShiftTypeDTO {
    private UUID id;
    private String name;
    private String description;
    private boolean active;
    private Date createdAt;
    private Date updatedAt;
    private String timeZone;
    private UUID tenantId;
}
