package com.kdu.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "shifts_type")
public class ShiftType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String description;
    private boolean active;
    @Column
    private String timeZone;
    @ManyToOne
    private Tenant tenant;
}