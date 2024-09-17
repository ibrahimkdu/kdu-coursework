package com.kdu.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String username;
    private int loggedIn;
    @Column
    private String timeZone;
    @ManyToOne
    private Tenant tenant;
}