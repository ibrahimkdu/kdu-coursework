package com.example.jpahw.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.UUID;


@Data
public class UserDTO {
    private String username;
    private int loggedIn;

    private String timeZone;

    @JsonDeserialize
    private UUID tenantID;

    public UserDTO(String username, int loggedIn, String timeZone, UUID tenantID){
        this.loggedIn = loggedIn;
        this.timeZone = timeZone;
        this.username = username;
        this.tenantID = tenantID;
    }
}
