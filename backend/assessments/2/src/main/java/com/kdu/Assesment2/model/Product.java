package com.kdu.Assesment2.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String desctiption;

    private double price;
    private int stockQuantities;
    @ManyToOne
    private Users users;
    @ManyToOne
    private ShoppingCart shoppingCart;
}
