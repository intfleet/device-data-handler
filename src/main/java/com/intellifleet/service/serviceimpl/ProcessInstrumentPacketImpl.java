package com.intellifleet.service.serviceimpl;


import com.intellifleet.entity.InstrumentFactsEntity;
import com.intellifleet.mapper.InstrumentPacketMapper;
import com.intellifleet.parser.dto.InstrumentPacketDTO;
import com.intellifleet.parser.dto.InstrumentPacketForRedisDTO;
import com.intellifleet.repository.InstrumentFactsRepository;
import com.intellifleet.service.ProcessInstrumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessInstrumentPacketImpl implements ProcessInstrumentService {

    final InstrumentFactsRepository instrumentFactsRepository;

    final InstrumentPacketMapper instrumentPacketMapper;

    @Override
    public void process(InstrumentPacketDTO instrumentPacketDTO) {
        try {
//            log.info(instrumentPacketDTO.toString());
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

    @Override
    public long process(List<InstrumentPacketDTO> instrumentPacketDTOList) {
        try {
            instrumentPacketDTOList.parallelStream()
                    .map(instrumentPacketMapper::toEntity)
                    .map(entity -> {
//                        entity.setTmsInstrument(LocalDateTime.now());
                        entity.setTmsCreate(LocalDateTime.now());
                        entity.setTxtNote("Intellifleet GPS data");
                        return entity;
                    })
                    .forEach(instrumentFactsRepository::save);
            return instrumentPacketDTOList.size();
        } catch (Exception ex) {
            log.error("Exception occurred inside process()", ex);
        }
        return 0;
    }

    @Override
    public InstrumentPacketForRedisDTO processToAddOnRedis(InstrumentPacketDTO instrumentPacketDTO) {
        log.info("instrumentPacketDTO: {}", instrumentPacketDTO.toString());
        return InstrumentPacketForRedisDTO.builder()
                .instrumentId(instrumentPacketDTO.txtInstrumentId())
                .resources(instrumentPacketDTO.txtResources())
                .tmsInstrument(instrumentPacketDTO.tmsInstrument())
                .latitude(instrumentPacketDTO.numLatitude())
                .longitude(instrumentPacketDTO.numLongitude())
                .build();
    }
}
