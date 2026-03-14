package com.intellifleet.service;

import com.intellifleet.parser.dto.InstrumentPacketDTO;

public interface ProcessInstrumentService {
    void process(InstrumentPacketDTO instrumentPacketDTO);
}
