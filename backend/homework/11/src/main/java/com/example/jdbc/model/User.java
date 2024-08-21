package com.example.jdbc.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private UUID id;
    private String userName;
    private int loggedIn;
    private String timeZone;
    private UUID tenantId;



    public User(String username, int loggedIn, String timeZone, String tenantId) {
    }
}
