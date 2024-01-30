package com.example.pouring;

import com.example.beverage.Beverage;
import com.example.beverage.BeverageCup;
import com.example.cup.Cup;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

public class PouringSession {
    @Getter
    private Beverage beverage;
    @Getter
    @Setter
    private Cup cup;
    @Getter
    @Setter
    private double volumePoured = 0.0;

    @Getter
    private final int pouringRate;

    public PouringSession(int pouringRate) {
        this.pouringRate = pouringRate;
    }

    public void setBeverage(Beverage newBeverage) {
        if (beverage == null || !beverage.equals(newBeverage)) {
            beverage = newBeverage;
            setCup(null);
        }
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

    @JsonIgnore
    public int getPourTime() {
        int totalVolumeToPour = getCup().getVolume();
        if (volumePoured >= totalVolumeToPour) {
            return 0;
        }

        double volumeToPour = totalVolumeToPour - volumePoured;
        return (int) (volumeToPour / pouringRate) * 1000;
    }

    public boolean canPour() {
        return beverage != null && cup != null && volumePoured < cup.getVolume();
    }
}
