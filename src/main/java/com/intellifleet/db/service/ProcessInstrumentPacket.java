package com.intellifleet.db.service;


import com.intellifleet.parser.dto.InstrumentPacketDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProcessInstrumentPacket {

    public void process(InstrumentPacketDTO instrumentPacketDTO) {
        try {
            log.info(instrumentPacketDTO.toString());

            //Need to add here db insertion process........
        } catch (Exception ex) {
            log.error("Exception occurred inside process()", ex);
        }
    }
}
