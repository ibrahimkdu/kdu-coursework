package com.kdu.repository;

import com.kdu.model.ShiftUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ShiftUserRepository extends JpaRepository<ShiftUser, UUID> {

}