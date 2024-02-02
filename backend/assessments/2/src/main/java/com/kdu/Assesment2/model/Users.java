package com.kdu.Assesment2.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "userss")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="full_name")
    private String fullName;
    @Column(name="email")
    private String email;
   @Column(name="password")
    private String password;


}
