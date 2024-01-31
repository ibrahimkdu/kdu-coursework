package com.example.jdbc.service;

import com.example.jdbc.DAO.ShiftUserDAO;
import com.example.jdbc.model.ShiftUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShiftUserService {
    @Autowired
    ShiftUserDAO shiftUserDAO;

    public void addShiftUser(ShiftUser shiftUser){
        shiftUserDAO.saveShiftUser(shiftUser);
    }

    public ShiftUser getShiftUserById(UUID id){
        return shiftUserDAO.getShiftUserById(id);
    }

}
