package com.kdu.Assesment2.repository;

import com.kdu.Assesment2.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ProductRepository  extends JpaRepository<Product, Integer> {


}