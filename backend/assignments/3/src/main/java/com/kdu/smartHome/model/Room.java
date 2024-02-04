package com.kdu.smartHome.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String roomName;

    @ManyToOne
    private House house;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Device> devices;
}



