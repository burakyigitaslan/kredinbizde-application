package com.patika.kredinbizdeservice.producer;

import com.patika.kredinbizdeservice.configuration.RabbitMQConfiguration;
import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;
import com.patika.kredinbizdeservice.producer.enums.NotificationType;
import com.patika.kredinbizdeservice.producer.strategy.NotificationContext;
import com.patika.kredinbizdeservice.producer.strategy.NotificationStrategy;
import com.patika.kredinbizdeservice.producer.strategy.impl.NotificationStrategyFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {

    private final RabbitMQConfiguration rabbitMQConfiguration;
    private final AmqpTemplate amqpTemplate;
    private final NotificationContext context;

    public void sendNotification(NotificationDTO notificationDTO) {
        NotificationType type = notificationDTO.getNotificationType();
        NotificationStrategy strategy = NotificationStrategyFactory.createStrategy(type);
        context.setStrategy(strategy);
        context.sendNotification(notificationDTO);

        amqpTemplate.convertSendAndReceive(rabbitMQConfiguration.getExchange(), rabbitMQConfiguration.getRoutingkey(), notificationDTO);
    }
}
