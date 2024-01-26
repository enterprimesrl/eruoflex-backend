package com.example.pouring;

import com.example.beverage.Beverage;
import com.example.cup.Cup;
import lombok.Data;

@Data
public class PouringSession {
    private Beverage beverage;
    private Cup cup;
    private double volumePoured = 0.0;

    public int getMaxPouringTime() {
        return (int) (cup.getVolume() / cup.getDefaultRate()) * 1000;
    }
}
