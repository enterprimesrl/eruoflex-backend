package com.example.pouring;

import com.example.beverage.Beverage;
import com.example.beverage.BeverageCup;
import com.example.cup.Cup;
import lombok.Data;

import java.util.Optional;

@Data
public class PouringSession {
    private Beverage beverage;
    private Cup cup;
    private double volumePoured = 0.0;

    public int getMaxPouringTime() {
        return (int) (cup.getVolume() / cup.getDefaultRate()) * 1000;
    }

    public double getPrice() {
        if (beverage == null) {
            throw new RuntimeException("Beverage not found");
        }

        Optional<BeverageCup> selectedCup = beverage.getCups().stream().filter(c -> c.getKey().equals(cup.getKey())).findFirst();

        if (selectedCup.isEmpty()) {
            throw new RuntimeException("Cup not found");
        }
        return selectedCup.get().getPrice();
    }
}
