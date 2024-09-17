package com.kdu.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "shift_users")
public class ShiftUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private Shift shift;
    @ManyToOne
    private User user;
    @ManyToOne
    private Tenant tenant;
}