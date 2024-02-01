package com.example.jdbc.mapper;

import com.example.jdbc.model.Shift;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ShiftRowMapper implements RowMapper<Shift> {
    @Override
    public Shift mapRow(ResultSet result, int rowNum) throws SQLException {
        Shift shift = new Shift();
        shift.setId(UUID.fromString(result.getString("id")));
        shift.setShiftTypeId(UUID.fromString(result.getString("shift_type_id")));
        shift.setName(result.getString("name"));
        shift.setDateStart(result.getDate("date_start").toLocalDate());
        shift.setDateEnd(result.getDate("date_end").toLocalDate());
        shift.setTimeStart(result.getTime("time_start").toLocalTime());
        shift.setTimeEnd(result.getTime("time_end").toLocalTime());
        shift.setCreatedAt(result.getTimestamp("created_at"));
        shift.setUpdatedAt(result.getTimestamp("updated_at"));
        shift.setTimeZone(result.getString("time_zone"));
        shift.setTenantId(UUID.fromString(result.getString("tenant_id")));
  return shift;
    }

}

