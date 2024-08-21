package com.example.jdbc.mapper;

import com.example.jdbc.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserRowMapper implements RowMapper<User> {


    @Override
    public User mapRow(ResultSet result, int rowNum) throws SQLException {
        User user = new User();
        user.setId(UUID.fromString(result.getString("id")));
        user.setUserName(result.getString("username"));
        user.setLoggedIn(result.getInt("loggedIn"));
        user.setTimeZone(result.getString("time_zone"));
        user.setTenantId(UUID.fromString(result.getString("tenant_id")));

        return user;
    }
}
