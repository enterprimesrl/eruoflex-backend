package com.example.pouring;

public class Cup {
    private final String size;
    private final String name;
    private final int defaultRate;
    private final int quantity;
    private final int price;

    public Cup(String size, String name, int defaultRate, int quantity, int price) {
        this.size = size;
        this.name = name;
        this.defaultRate = defaultRate;
        this.quantity = quantity;
        this.price = price;
    }
}
