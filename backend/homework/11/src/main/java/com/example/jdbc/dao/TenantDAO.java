package com.example.jdbc.dao;

import com.example.jdbc.mapper.TenantRowMapper;
import com.example.jdbc.model.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class TenantDAO {
    final JdbcTemplate jdbcTemplate;
    @Autowired
    public TenantDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveTenant(Tenant tenant){
        String sql = "INSERT INTO tenants VALUES(?,?)";
        jdbcTemplate.update(sql, tenant.getId(),tenant.getName());
    }

    public Tenant getTenantById(UUID id){
        String sql ="SELECT * FROM tenants WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new TenantRowMapper(),id);
    }

    public int updateNameForId(int id, String name){
        String sql = "UPDATE tenants SET name = ? WHERE id = ?";
        return jdbcTemplate.update(sql, name, id);
    }
}
