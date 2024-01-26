package com.example.beverage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tccc.kos.commons.core.service.AbstractService;
import com.tccc.kos.commons.util.KosUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.List;

@Slf4j
public class BeverageService extends AbstractService {

    @Getter
    private List<Beverage> beverages;

    public BeverageService() {
        String JSON_FILENAME = "beverages.json";

        try {
            beverages = KosUtil.getMapper().readValue(
                    getClass().getClassLoader().getResourceAsStream(JSON_FILENAME),
                    new TypeReference<List<Beverage>>() {}
            );
        }

        catch(Exception e) {
            log.error("Error reading json file: " + JSON_FILENAME);
            throw new RuntimeException(e);
        }
    }

}
