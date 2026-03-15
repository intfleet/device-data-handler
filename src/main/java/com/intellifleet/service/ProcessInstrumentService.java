package com.intellifleet.service;

import com.intellifleet.parser.dto.InstrumentPacketDTO;

import java.util.List;

public interface ProcessInstrumentService {
    void process(InstrumentPacketDTO instrumentPacketDTO);
    long process(List<InstrumentPacketDTO> instrumentPacketDTOList);
}
