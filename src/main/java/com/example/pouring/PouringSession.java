package com.example.pouring;

import com.example.beverage.Beverage;
import com.example.cup.Cup;
import lombok.Data;

@Data
public class PouringSession {
    private Beverage beverage;
    private Cup cup;
    private Double volumePoured = 0.0;
}
