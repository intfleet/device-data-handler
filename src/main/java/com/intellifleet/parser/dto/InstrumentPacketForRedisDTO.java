package com.intellifleet.parser.dto;

import lombok.Builder;

@Builder
public record InstrumentPacketForRedisDTO(
        String instrumentId,
        String resources,
        String tmsInstrument,

        Double latitude,
        Double longitude

) {
}
