package com.example.mbmbmb.shopping;

/**
 * Created by mbmbmb on 12/12/2016.
 */
public class Product {
    private String name;
    private float price;
    private int quantity;

    public Product(String name, float price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
