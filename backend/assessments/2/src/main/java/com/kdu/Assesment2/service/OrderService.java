package com.kdu.Assesment2.service;


import com.kdu.Assesment2.model.Order;
import com.kdu.Assesment2.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository)
    {
        this.orderRepository=orderRepository;
    }
    public void saveOrder(Order order)
    {
        orderRepository.save(order);
    }


    public Order getUserById(Integer orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public List<Order> getAllAddress() {
        return orderRepository.findAll();
    }

}
