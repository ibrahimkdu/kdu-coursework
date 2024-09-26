package com.kdu.Assesment2.repository;

import com.kdu.Assesment2.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository  extends JpaRepository<Order, Integer> {


}