package com.kdu.smarthome.repository;

import com.kdu.smarthome.dto.request.RequestUserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<RequestUserDTO, Integer> {

    RequestUserDTO findByUsername(String username);
}
