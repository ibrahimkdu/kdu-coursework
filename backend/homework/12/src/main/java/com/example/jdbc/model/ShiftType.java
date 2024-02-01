package com.example.jdbc.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiftType {
    private UUID id;
    private String name;
    private String description;
    private boolean active;
    private Date createdAt;
    private Date updatedAt;
    private String timeZone;
    private UUID tenantId;

    public ShiftType(String uqName, String description, boolean active, Instant createdAt, Instant updatedAt, String timeZone, String tenantId) {
    }
}
