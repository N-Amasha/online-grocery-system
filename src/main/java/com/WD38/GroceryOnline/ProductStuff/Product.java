package com.WD38.GroceryOnline.ProductStuff;

import java.time.LocalDate;

public class Product {
    private String id;
    private String name;
    private String category;
    private double price;
    private int quantity;
    private LocalDate expDate;

    public Product(String id, String name, String category, double price, int quantity, LocalDate expDate) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.expDate = expDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    // for testing
    public void displayDetails() {
        System.out.println(
                id + " | " +
                        name + " | " +
                        category + " | Rs." +
                        price + " | " +
                        quantity + " | " +
                        expDate
        );        
    }
}