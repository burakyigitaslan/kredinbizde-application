package com.patika.kredinbizdeservice.producer.strategy.impl;

import com.patika.kredinbizdeservice.producer.enums.NotificationType;
import com.patika.kredinbizdeservice.producer.strategy.NotificationStrategy;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class NotificationStrategyFactory {
    public static NotificationStrategy createStrategy(NotificationType type) {
        return switch (type) {
            case EMAIL -> new EmailNotificationStrategy();
            case SMS -> new SmsNotificationStrategy();
            case MOBILE_NOTIFICATION -> new MobileNotificationStrategy();
            default -> throw new IllegalArgumentException("Invalid notification type: " + type);
        };
    }
}
