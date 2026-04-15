package com.example.evergreen.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private double price;
    private int stockQuantity;
    private boolean isDailyDrop;
    private Double discountPrice; // Use Double so it can be null

    @Column(name = "image_url")
    private String imageUrl;

    @Column(length = 500)
    private String description;
}