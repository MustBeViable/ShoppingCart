package org.example;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private final List<Item> cart = new ArrayList<>();

    public void addToCart(Item item) {
        this.cart.add(item);
    }

    public double totalValue() {
        double total = 0.0;
        for (Item item : cart) {
            total += item.totalPrice();
        }
        return total;
    }

    public int itemCount() {
        return cart.size();
    }

    public List<Item> getItems() {
        return new ArrayList<>(cart);
    }
}