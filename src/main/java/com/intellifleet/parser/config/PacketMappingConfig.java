package com.intellifleet.parser.config;


import com.intellifleet.parser.dto.InstrumentConfigDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "packet")
public class PacketMappingConfig {

    private Map<String, InstrumentConfigDTO> devices;

    public InstrumentConfigDTO getMapping(String header){
        return devices.get(header);
    }

    public Set<String> getHeaders(){
        return devices.keySet();
    }
}
