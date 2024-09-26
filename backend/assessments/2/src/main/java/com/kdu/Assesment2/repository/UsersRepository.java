package com.kdu.Assesment2.repository;

import com.kdu.Assesment2.model.Product;
import com.kdu.Assesment2.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository  extends JpaRepository<Users, Integer> {


}