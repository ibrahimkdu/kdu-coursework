package com.kdu.smartHome.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String kickstonId;
    private String deviceName;

    private String devicePassword;

    private String manufactureDateTime;

    private String manufactureFactoryPlace;


}

//● kickston_id (Type: String) - The identifier for the inventory item.
//● device_username (Type: String) - The username associated with the device.
//        ● device_password (Type: String) - The password associated with the device.
//        ● manufacture_date_time (Type: String) - The manufacturing date and time of
//the device (format: "yyyy-MM-dd'T'HH:mm:ss").
//        ● manufacture_factory_place (Type: String) - The place where the device was
//manufactured.