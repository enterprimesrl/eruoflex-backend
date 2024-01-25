package com.example.pouring;

import lombok.Data;

@Data
public class Cup {
    private String size;
    private String name;
    private int defaultRate;
    private int quantity;
    private int price;

}
