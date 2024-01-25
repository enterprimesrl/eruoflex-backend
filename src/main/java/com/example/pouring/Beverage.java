package com.example.pouring;

import lombok.Data;

import java.util.List;

@Data
public class Beverage {
    private String id;
    private String abbr;
    private String name;
    private List<Cup> cups;
}
