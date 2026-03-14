package com.intellifleet.mapper;

import com.intellifleet.entity.InstrumentFactsEntity;
import com.intellifleet.parser.dto.InstrumentPacketDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InstrumentPacketMapper {

    InstrumentFactsEntity toEntity(InstrumentPacketDTO dto);

    InstrumentPacketDTO toDto(InstrumentFactsEntity entity);

}