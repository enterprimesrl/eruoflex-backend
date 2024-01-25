package com.example.pouring;

import com.tccc.kos.commons.util.json.StreamingJsonReader;

import java.util.ArrayList;
import java.util.List;

public class BeverageStreamingReader extends StreamingJsonReader {
    private final List<Beverage> beverages = new ArrayList<>();
    private Beverage beverage;

    public BeverageStreamingReader() {
        addCallback("[{", parser -> beverage = new Beverage());

        addCallback("[}", parser -> beverages.add(beverage));
    }
}
