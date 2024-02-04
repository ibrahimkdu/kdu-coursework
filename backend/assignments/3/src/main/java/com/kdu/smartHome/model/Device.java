package com.kdu.smartHome.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String kickstonId;
    private String deviceName;
    private String devicePassword;

    @ManyToOne(cascade = CascadeType.ALL)
    private House house;

    @ManyToOne(cascade = CascadeType.ALL)
    private Room room;
}
