package com.kdu.smarthome.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Component
public class TokenGenerator {

    public static final String JWT_KEY = "jxgEQeXHuPq8VdhhYFNkANdudQ53YUn1";
    public static final String JWT_HEADER = "Authorization";

    public static String generateToken(Authentication authentication, HttpServletResponse response) {
        if (null != authentication) {
            SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
            String jwt =   Jwts.builder().claim("username", authentication.getName())
                    .claim("roles", populateAuthorities(authentication.getAuthorities()))
                    .issuedAt(new Date())
                    .expiration(new Date((new Date()).getTime() + 30000000))
                    .signWith(key).compact();

            response.setHeader(JWT_HEADER, jwt);
            return jwt;

        }
return  null;
    }

   public static String getUsernameFromJWT(String token)
   {
       Claims claims = Jwts.parser()
               .setSigningKey(JWT_KEY)
               .build()
               .parseClaimsJws(token)
               .getBody();
       return claims.getSubject();
   }

    public static Claims validateToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
           return Jwts.parser()
                   .verifyWith(key)
                   .build()
                   .parseSignedClaims(token)
                   .getPayload();

        }
        catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("Invalid Jwt Token");
        }
    }
    private static String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }
}