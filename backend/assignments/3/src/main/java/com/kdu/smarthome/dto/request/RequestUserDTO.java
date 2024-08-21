package com.kdu.smarthome.dto.request;

import jakarta.persistence.*;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class RequestUserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String role;
    private String name;
    private String firstName;
    private String lastName;
    private String email;
}
