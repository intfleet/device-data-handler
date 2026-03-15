package com.intellifleet.mapper;

import com.intellifleet.entity.InstrumentFactsEntity;
import com.intellifleet.parser.dto.InstrumentPacketDTO;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface InstrumentPacketMapper {
    @Mapping(source = "tmsInstrument", target = "tmsInstrument")
    InstrumentFactsEntity toEntity(InstrumentPacketDTO dto);


    InstrumentPacketDTO toDto(InstrumentFactsEntity entity);

    default LocalDateTime map(String value) {
        if (value == null) {
            return null;
        }
        return LocalDateTime.parse(StringUtils.left(value, 12), DateTimeFormatter.ofPattern("yyMMddHHmmss"));
    }

}