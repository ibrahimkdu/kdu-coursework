package com.kdu.smarthome.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name="houses")
@RequiredArgsConstructor
public class HouseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    String houseName;
    String address;
}
