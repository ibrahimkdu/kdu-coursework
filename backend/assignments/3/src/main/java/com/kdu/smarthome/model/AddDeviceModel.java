package com.kdu.smarthome.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@Table(name="devices_added")
public class AddDeviceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    DeviceModel deviceModel;
    @ManyToOne
    RoomModel roomModel;

    @ManyToOne
    HouseModel houseModel;

    @ManyToOne
    private HouseUser houseUser;
}
