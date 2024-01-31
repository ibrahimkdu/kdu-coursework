package com.example.jdbc.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shift {
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

    public Shift(UUID id, String name, LocalDate dateStart, LocalDate dateEnd, LocalTime timeStart, LocalTime timeEnd, Instant createdAt, Instant updatedAt, String timeZone) {
    }
}
