package com.patika.kredinbizdeservice.kafkaconsumer.kafkaconsumer;

import com.patika.kredinbizdeservice.configuration.KafkaConfiguration;
import com.patika.kredinbizdeservice.model.Log;
import com.patika.kredinbizdeservice.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class LogConsumer {
    private final LogRepository logRepository;

    @Autowired
    public LogConsumer(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @KafkaListener(topics = KafkaConfiguration.LOG_TOPIC, groupId = "log-group")
    public void consumeLog(String logMessage) {

        Log log = new Log();
        log.setMessage(logMessage);
        logRepository.save(log);
    }
}
