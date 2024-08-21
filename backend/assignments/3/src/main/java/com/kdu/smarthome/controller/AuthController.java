package com.kdu.smarthome.controller;

import com.kdu.smarthome.config.UserDetailServiceImpl;
import com.kdu.smarthome.dto.request.RequestUserDTO;
import com.kdu.smarthome.dto.response.ResponseAuthDTO;
import com.kdu.smarthome.filter.TokenGenerator;
import com.kdu.smarthome.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/auth")
public class AuthController {
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private UserDetailServiceImpl userDetailServiceImpl;

    @Autowired
    AuthController(UserService userService, AuthenticationManager authenticationManager, UserDetailServiceImpl userDetailServiceImpl) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userDetailServiceImpl = userDetailServiceImpl;
    }

    @PostMapping("register")
    public HttpEntity<ResponseAuthDTO> addUser(@RequestBody RequestUserDTO requestUserDTO, HttpServletResponse response) {
        String password = requestUserDTO.getPassword();
        try {
            requestUserDTO.setRole("ROLE_USER");
            if (requestUserDTO.getName() == null) {
                requestUserDTO.setName(requestUserDTO.getFirstName());
            }
            userService.addUser(requestUserDTO);
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestUserDTO.getUsername(), password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = TokenGenerator.generateToken(authentication, response);
            if (jwtToken == null) {
                throw new RuntimeException("Token is NULL");
            }
            return ResponseEntity.ok(new ResponseAuthDTO("User is added successfully", jwtToken, HttpStatus.OK));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseAuthDTO("user not added", null, HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
