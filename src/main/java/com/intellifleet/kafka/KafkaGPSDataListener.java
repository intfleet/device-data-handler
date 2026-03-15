package com.intellifleet.kafka;


import com.intellifleet.service.ProcessInstrumentService;
import com.intellifleet.service.serviceimpl.ProcessInstrumentPacketImpl;
import com.intellifleet.parser.services.GPSDataParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ConditionalOnProperty(name = "kafka.enabled", havingValue = "true")
public class KafkaGPSDataListener {

    @Autowired
    GPSDataParser gpsDataParser;

    @Autowired
    ProcessInstrumentService processInstrumentPacket;

    long start = 0;

    @KafkaListener(topics = "topic-gps-data", groupId = "group-gps-data")
    public void consumeEvents(String gpsData) {
//        log.info("Consumer consume the message {} ", gpsData);
        if(gpsData.trim().startsWith("START")){
            log.info("Started executing....");
            start = System.currentTimeMillis();
        } else if(gpsData.trim().startsWith("END")){
            long end = System.currentTimeMillis();
            log.info("End execution....");
            log.info("Execution Time : " + (end - start) + " ms");
        } else {
            processInstrumentPacket.process(gpsDataParser.parse(gpsData, 8080));
        }

    }
}
