package com.WD38.GroceryOnline.apibodies;

import com.WD38.GroceryOnline.ProductStuff.Product;

public class ProductQuantityTemplate {
    private String name;
    private int quantity;

    public ProductQuantityTemplate() {
    }

    public ProductQuantityTemplate(Product product) {
        this.name = product.getName();
        this.quantity = product.getQuantity();
    }

    public ProductQuantityTemplate(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }


    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
