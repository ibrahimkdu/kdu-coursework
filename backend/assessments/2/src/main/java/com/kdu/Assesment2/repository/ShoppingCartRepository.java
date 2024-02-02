package com.kdu.Assesment2.repository;

import com.kdu.Assesment2.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {


}