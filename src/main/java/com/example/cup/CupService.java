package com.example.cup;

import com.fasterxml.jackson.core.type.TypeReference;
import com.tccc.kos.commons.core.service.AbstractService;
import com.tccc.kos.commons.util.KosUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class CupService extends AbstractService {

    @Getter
    private List<Cup> cups;
    private Map<String, Cup> cupsMap;

    public CupService() {
        String JSON_FILENAME = "cups.json";
        try {
            cups = KosUtil.getMapper().readValue(JSON_FILENAME, new TypeReference<List<Cup>>() {
            });

            cups.stream().forEach((Cup cup) -> {
                cupsMap.put(cup.getKey(), cup);
            });
        } catch (Exception e) {
            log.error("Error reading json file: " + JSON_FILENAME);
            throw new RuntimeException(e);
        }
    }

    public Cup getCup(String key) {
        return cupsMap.get(key);
    }
}
