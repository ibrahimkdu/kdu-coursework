package com.kdu.Assesment2.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @ManyToOne
    private Product products;
}
