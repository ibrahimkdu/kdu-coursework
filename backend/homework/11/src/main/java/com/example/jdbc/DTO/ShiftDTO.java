package com.example.jdbc.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ShiftDTO {
    private UUID id;
    private UUID shiftTypeId;
    private String name;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private Date createdAt;
    private Date updatedAt;
    private String timeZone;
    private UUID tenantId;
}
