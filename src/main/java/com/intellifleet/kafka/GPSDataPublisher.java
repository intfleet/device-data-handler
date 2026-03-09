package com.intellifleet.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class GPSDataPublisher {

    @Value("${kafka.enabled:false}")
    private boolean kafkaEnabled;

    @Autowired(required = false)
    private KafkaTemplate<String,Object> template;

    public void sendMessageToTopic(String message){
        if(!kafkaEnabled || template == null){
            log.debug("Kafka disabled. Message not sent: {}", message);
            return;
        }

        CompletableFuture<SendResult<String, Object>> future = template.send("topic-gps-data", message);
        future.whenComplete((result,ex)->{
            if (ex == null) {
                System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
        });
    }
}
