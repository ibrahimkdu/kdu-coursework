package com.kdu.smarthome.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "device")
public class DeviceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String kickstonId;
    private String deviceUsername;
    private String devicePassword;
    private LocalDateTime manufactureDateTime;

    private String manufactureFactoryPlace;
}
