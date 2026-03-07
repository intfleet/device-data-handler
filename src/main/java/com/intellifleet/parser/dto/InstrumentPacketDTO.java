package com.intellifleet.parser.dto;

import lombok.Builder;

@Builder
public record InstrumentPacketDTO (

        String txtInstrumentId,
        String txtResources,
        String tmsInstrument,

        Double numLatitude,
        Double numLongitude,

        Integer intCurrentSpeed,

        Integer intPortNumber,

        String txtLocationDetails
) {}