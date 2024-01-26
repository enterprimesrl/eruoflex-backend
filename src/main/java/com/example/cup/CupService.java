package com.example.cup;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tccc.kos.commons.core.service.AbstractService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.List;

@Slf4j
public class CupService extends AbstractService {

    @Getter
    private List<Cup> cups;

    public CupService() {
        String JSON_FILENAME = "cups.json";
        try(InputStream in = getClass().getClassLoader().getResourceAsStream(JSON_FILENAME)) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
            String jsonString = mapper.writeValueAsString(jsonNode);
            cups = mapper.readValue(jsonString, new TypeReference<List<Cup>>() {});
        }
        catch(Exception e) {
            log.error("Error reading json file: " + JSON_FILENAME);
            throw new RuntimeException(e);
        }
    }


    public int getVolume(String key) {
//        cups.forEach(cup -> {
//            if (cup.getKey().equals(key)) {
//                return cup.getVolume();
//            }
//        });




        throw new RuntimeException("Cup not found: " + key);
    }
}
