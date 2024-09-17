package com.kdu.controller;

import com.kdu.model.User;
import com.kdu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/app/v1")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/newuser")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>("User saved successfully", HttpStatus.CREATED);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable UUID userId) {
        Optional<User> retrievedUser = userService.get(userId);
        if (retrievedUser != null) {
            return new ResponseEntity<>(retrievedUser.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> userPage = userService.getAll();
        return ResponseEntity.ok(userPage);
    }


}
