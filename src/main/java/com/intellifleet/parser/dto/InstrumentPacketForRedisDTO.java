package com.intellifleet.parser.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record InstrumentPacketForRedisDTO(
        String instrumentId,
        String resources,
        String tmsInstrument,

        BigDecimal latitude,
        BigDecimal longitude

) {
}
