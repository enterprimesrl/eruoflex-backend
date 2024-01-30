package com.example.beverage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.tccc.kos.commons.core.service.AbstractService;
import com.tccc.kos.commons.util.KosUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class BeverageService extends AbstractService {

    @Getter
    private List<Beverage> beverages;

    private Map<String, Beverage> beveragesMap = new HashMap<>();

    public BeverageService() {
        String JSON_FILENAME = "beverages.json";

        try {
            beverages = KosUtil.getMapper().readValue(getClass().getClassLoader().getResourceAsStream(JSON_FILENAME), new TypeReference<List<Beverage>>() {
            });

            beverages.stream().forEach((Beverage beverage) -> {
                beveragesMap.put(beverage.getId(), beverage);
            });
        } catch (Exception e) {
            log.error("Error reading json file: " + JSON_FILENAME);
            throw new RuntimeException(e);
        }
    }

    public Beverage getBeverage(String key) {
        return beveragesMap.get(key);
    }
}
