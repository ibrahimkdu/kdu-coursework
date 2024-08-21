package com.kdu.smarthome.utils;

import com.kdu.smarthome.dto.request.RequestHouseDTO;
import com.kdu.smarthome.dto.request.RequestUserDTO;
import com.kdu.smarthome.model.HouseModel;
import com.kdu.smarthome.enums.HouseRole;
import com.kdu.smarthome.model.HouseUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


@Slf4j
public class HouseMapping {

    public static HouseModel toHouseModel(RequestHouseDTO requestHouseDTO, HouseRole roleHouse) {
        HouseModel house = new HouseModel();
        house.setHouseName(requestHouseDTO.getHouse_name());
        house.setAddress(requestHouseDTO.getAddress());
        return house;
    }
    public static HouseUser toHouseUser(RequestHouseDTO requestHouseDTO, HouseRole houseRole) {
        HouseUser houseUser = new HouseUser();
        houseUser.setHouseRole(houseRole);
        String username = currentUserName();
        RequestUserDTO requestUserDTO = new RequestUserDTO();
        requestUserDTO.setUsername(username);
        houseUser.setUser(requestUserDTO);
        return houseUser;
    }
    public static String currentUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
