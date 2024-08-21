package com.kdu.smarthome.service;

import com.kdu.smarthome.config.UserDetailServiceImpl;
import com.kdu.smarthome.dto.request.RequestUserDTO;
import com.kdu.smarthome.dto.response.ResponseAuthDTO;
import com.kdu.smarthome.filter.TokenGenerator;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailServiceImpl userDetailServiceImpl;

    @Autowired
    public AuthenticationService(UserService userService, AuthenticationManager authenticationManager,
                                 UserDetailServiceImpl userDetailServiceImpl) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userDetailServiceImpl = userDetailServiceImpl;
    }
    public ResponseEntity<ResponseAuthDTO> registerUser(RequestUserDTO user, HttpServletResponse response) {
        String pass = user.getPassword();
        try {
            user.setRole("ROLE_USER");
            if (user.getName() == null) {
                user.setName(user.getFirstName() + user.getLastName());
            }
            userService.addUser(user);
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), pass));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(user.getUsername());
            String jwtToken = TokenGenerator.generateToken(authentication, response);
            if (jwtToken == null) {
                throw new RuntimeException("jwt token is NULL");
            }
            return ResponseEntity.ok(new ResponseAuthDTO("user is  added successfully", jwtToken, HttpStatus.OK));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseAuthDTO(" user failed to add", null, HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
