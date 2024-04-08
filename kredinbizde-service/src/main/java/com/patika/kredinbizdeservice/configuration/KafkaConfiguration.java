package com.patika.kredinbizdeservice.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfiguration {
    public static final String LOG_TOPIC = "log-topic";

    @Bean
    public NewTopic logTopic() {
        return TopicBuilder.name(LOG_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }
}