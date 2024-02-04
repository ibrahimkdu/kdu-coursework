package com.kdu.smartHome.repository;

import com.kdu.smartHome.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String username);
    // You can add more custom queries if needed
}

