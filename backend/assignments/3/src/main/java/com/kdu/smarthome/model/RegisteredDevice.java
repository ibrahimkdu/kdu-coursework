package com.kdu.smarthome.model;

import jakarta.persistence.*;
import com.kdu.smarthome.dto.request.RequestUserDTO;
import lombok.Data;
@Data
@Entity
@Table(name="registered_devices")
public class RegisteredDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private RequestUserDTO requestUserDTO;
    @OneToOne
    private DeviceModel deviceModel;
}
