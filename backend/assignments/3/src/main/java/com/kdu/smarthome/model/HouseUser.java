package com.kdu.smarthome.model;

import com.kdu.smarthome.dto.request.RequestUserDTO;
import com.kdu.smarthome.enums.HouseRole;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "house_users")
@Data
public class HouseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private HouseModel house;
    @ManyToOne
    private RequestUserDTO user;
    @Enumerated(EnumType.STRING)
    HouseRole houseRole;

}
