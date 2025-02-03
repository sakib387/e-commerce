package com.example.notification.service;


import com.example.notification.dto.PaymentDTO;
import com.example.notification.model.Notification;
import com.example.notification.repository.NotificationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.example.notification.dto.OrderDTO;

import java.time.LocalDateTime;

import static com.example.notification.model.NotificationType.PAYMENT_CONFIRMATION;


@Service
public class NotificationsConsumer {

    private NotificationRepository repository;

    public NotificationsConsumer(NotificationRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "paymentTopic-2" ,groupId = "order-group")
    public void consume(PaymentDTO payment) {
        System.out.println("Received payment: " + payment);
        this.repository.save(
                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(payment)
                        .build()
        );
    }

    @KafkaListener(topics = "orderTopic-2" ,groupId = "order-group")
    public void consume(OrderDTO order) {
        System.out.println("Received Order: " + order);
        this.repository.save(
                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(order)
                        .build()
        );
    }
}
