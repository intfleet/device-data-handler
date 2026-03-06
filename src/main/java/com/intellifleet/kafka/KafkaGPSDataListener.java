package com.intellifleet.kafka;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaGPSDataListener {

    @KafkaListener(topics = "topic-gps-data", groupId = "group-gps-data")
    public void consumeEvents(String gpsData) {
        log.info("Consumer consume the message {} ", gpsData);
    }
}
