package com.example.jdbc.mapper;

import com.example.jdbc.model.ShiftUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

public class ShiftUserRowMapper implements RowMapper<ShiftUser> {
    @Override
    public ShiftUser mapRow(ResultSet result, int rowNum) throws SQLException {
        ShiftUser shiftUser = new ShiftUser();
        shiftUser.setId(UUID.fromString(result.getString("id")));
        shiftUser.setShiftId(UUID.fromString(result.getString("shift_id")));
        shiftUser.setUserId(UUID.fromString(result.getString("user_id")));
        shiftUser.setTenantId(UUID.fromString(result.getString("tenant_id")));



        return shiftUser;
    }
}
