package com.example.mbmbmb.shopping;

/**
 * Created by mbmbmb on 12/12/2016.
 */
public class ShoppingListItem {
    private String name;
    private float price;
    private int quantity;

    public ShoppingListItem(String name,float price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}
