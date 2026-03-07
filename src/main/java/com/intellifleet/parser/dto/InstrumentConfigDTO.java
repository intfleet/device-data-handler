package com.intellifleet.parser.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Device config
 */
@Getter
public class InstrumentConfigDTO {

    @Setter
    private String sep;
    private String mapping;

    private Map<String,Integer> fieldIndexes;

    public void setMapping(String mapping) {

        this.mapping = mapping;

        fieldIndexes = new HashMap<>();

        for(String pair : mapping.split(",")) {

            String[] kv = pair.split(":");

            fieldIndexes.put(
                    kv[0].trim(),
                    Integer.parseInt(kv[1].trim())
            );
        }
    }

}
