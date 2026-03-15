package com.intellifleet.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@ConditionalOnProperty(name = "kafka.enabled", havingValue = "true")
public class KafkaProducerConfig {

    @Bean
    public NewTopic createTopic(){
        return TopicBuilder.name("topic-gps-data")
                .partitions(6)
                .replicas(1)
                .build();
    }
}
