package com.intellifleet.service.serviceimpl;


import com.intellifleet.entity.InstrumentFactsEntity;
import com.intellifleet.mapper.InstrumentPacketMapper;
import com.intellifleet.parser.dto.InstrumentPacketDTO;
import com.intellifleet.repository.InstrumentFactsRepository;
import com.intellifleet.service.ProcessInstrumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessInstrumentPacketImpl implements ProcessInstrumentService {

    final InstrumentFactsRepository instrumentFactsRepository;

    final InstrumentPacketMapper instrumentPacketMapper;

    @Override
    public void process(InstrumentPacketDTO instrumentPacketDTO) {
        try {
            log.info(instrumentPacketDTO.toString());
            InstrumentFactsEntity entity = instrumentPacketMapper.toEntity(instrumentPacketDTO);
            entity.setTmsInstrument(LocalDateTime.now());
            entity.setTmsCreate(LocalDateTime.now());
            entity.setTxtNote("Intellifleet GPS data");
            instrumentFactsRepository.save(entity);
//            log.info("Saved Entity: {}", entity);
            //Need to add here db insertion process...........
        } catch (Exception ex) {
            log.error("Exception occurred inside process()", ex);
        }
    }
}
