package com.intellifleet.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "kafka.enabled", havingValue = "true")
public class KafkaProducerConfig {

    @Bean
    public NewTopic createTopic(){
        return new NewTopic("topic-gps-data", 3, (short) 1);
    }
}
