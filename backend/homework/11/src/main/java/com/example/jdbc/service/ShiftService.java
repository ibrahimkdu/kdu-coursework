package com.example.jdbc.service;

import com.example.jdbc.DAO.ShiftDAO;
import com.example.jdbc.DAO.ShiftUserDAO;
import com.example.jdbc.model.Shift;
import com.example.jdbc.model.ShiftUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShiftService {

    @Autowired
    ShiftDAO shiftDAO;
    public void addShift(Shift shift){
        shiftDAO.saveShift(shift);
    }
    public Shift getShiftById(UUID id){
        return shiftDAO.getShiftByid(id);
    }
}
