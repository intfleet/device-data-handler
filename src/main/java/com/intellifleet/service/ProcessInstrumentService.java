package com.intellifleet.service;

import com.intellifleet.parser.dto.InstrumentPacketDTO;
import com.intellifleet.parser.dto.InstrumentPacketForRedisDTO;
import org.springframework.cache.annotation.CachePut;

import java.util.List;

public interface ProcessInstrumentService {
    void process(InstrumentPacketDTO instrumentPacketDTO);
    long process(List<InstrumentPacketDTO> instrumentPacketDTOList);

    @CachePut(value = "gps-data", key = "#instrumentPacketDTO.txtInstrumentId()")
    InstrumentPacketForRedisDTO processToAddOnRedis(InstrumentPacketDTO instrumentPacketDTO);
}
