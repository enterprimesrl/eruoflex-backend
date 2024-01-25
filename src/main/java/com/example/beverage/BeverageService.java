package com.example.beverage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tccc.kos.commons.core.service.AbstractService;
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
        try(InputStream in = getClass().getClassLoader().getResourceAsStream(JSON_FILENAME)) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
            String jsonString = mapper.writeValueAsString(jsonNode);
            beverages = mapper.readValue(jsonString, new TypeReference<List<Beverage>>() {});
        }
        catch(Exception e) {
            log.error("Error reading json file: " + JSON_FILENAME);
            throw new RuntimeException(e);
        }
    }
}
