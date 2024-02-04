package com.kdu.smarthome.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="rooms")
public class RoomModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    String roomName;
    @ManyToOne
    HouseModel houseModel;
}
