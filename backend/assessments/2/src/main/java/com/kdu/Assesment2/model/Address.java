package com.kdu.Assesment2.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
     private String street;
     private String city;
     private String postalCode;

     private String nickname;
     @ManyToOne
    private Users users;
}
