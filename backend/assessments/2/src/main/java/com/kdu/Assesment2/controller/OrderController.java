package com.kdu.Assesment2.controller;

import com.kdu.Assesment2.model.Address;
import com.kdu.Assesment2.model.Order;
import com.kdu.Assesment2.service.AddressService;
import com.kdu.Assesment2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService)
    {
        this.orderService=orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<String> saveShift(@RequestBody Order order) {
        orderService.saveOrder(order);
        return new ResponseEntity<>("Order saved successfully", HttpStatus.CREATED);
    }
    @GetMapping("/order/{orderId}")
    public ResponseEntity<Order> getShiftById(@PathVariable Integer orderId) {
        Order retrievedShift = orderService.getUserById(orderId);
        if (retrievedShift != null) {
            return new ResponseEntity<>(retrievedShift, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/order")
    public ResponseEntity<List<Order>> getAllUsers() {
        List<Order> allShifts = orderService.getAllAddress();
        return new ResponseEntity<>(allShifts, HttpStatus.OK);
    }
}
