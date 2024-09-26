package com.kdu.Assesment2.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private LocalDate orderDate;

    private double totalAmount;

    @ManyToOne
    private Address address;
    @OneToOne
    private ShoppingCart shoppingCart;


}
