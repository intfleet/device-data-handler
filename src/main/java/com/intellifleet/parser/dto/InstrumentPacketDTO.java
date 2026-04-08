package com.intellifleet.parser.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record InstrumentPacketDTO (

        String txtInstrumentId,
        String txtResources,
        String tmsInstrument,

        BigDecimal numLatitude,
        BigDecimal numLongitude,

        Integer intCurrentSpeed,

        Integer intPortNumber,

        String txtLocationDetails
) {}