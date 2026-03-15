package com.intellifleet.kafka;


import com.intellifleet.service.ProcessInstrumentService;
import com.intellifleet.service.serviceimpl.ProcessInstrumentPacketImpl;
import com.intellifleet.parser.services.GPSDataParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
@ConditionalOnProperty(name = "kafka.enabled", havingValue = "true")
public class KafkaGPSDataListener {

    @Autowired
    GPSDataParser gpsDataParser;

    @Autowired
    ProcessInstrumentService processInstrumentPacket;

    private final AtomicLong counter = new AtomicLong();
    long start = 0;

    @KafkaListener(topics = "topic-gps-data", groupId = "group-gps-data")
    public void consumeEvents(String gpsData) {
//        log.info("Consumer consume the message {} ", gpsData);
        processInstrumentPacket.process(gpsDataParser.parse(gpsData, 8080));
        counter.incrementAndGet();
    }

    @Scheduled(fixedRate = 1000)
    public void report() {
        if(counter.get() > 0) {
            log.info("TPS = {}", counter.getAndSet(0));
        }
    }
}
