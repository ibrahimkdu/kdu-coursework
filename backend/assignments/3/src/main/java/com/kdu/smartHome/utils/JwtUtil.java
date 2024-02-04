package com.kdu.smartHome.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdu.smartHome.model.User;
import io.jsonwebtoken.*;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET = "your-secret-key";
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static String generateToken(User user) {
        return Jwts.builder()
                .claim("id",user.getId())
                .claim("userName",user.getUserName())
                .claim("password",user.getPassword())
                .claim("name",user.getName())
                .claim("firsName",user.getFirstName())
                .claim("lastName",user.getLastName())
                .claim("emailId",user.getEmailId())
                .setSubject(user.getUserName())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
    public static User extractUser(String token) {
        Claims temp= Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
        ObjectMapper mapper = new ObjectMapper();
        User customer = mapper.convertValue(temp, User.class);
        return customer;
    }
}

