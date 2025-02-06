package com.example.notification.service;


import com.example.notification.dto.PaymentDTO;
import com.example.notification.dto.PaymentSuccessDTO;
import com.example.notification.dto.ShipmentSuccess;
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

    @KafkaListener(topics = "payment-success-1" ,groupId = "order-payment-group")
    public void consume(PaymentSuccessDTO payment) {
        System.out.println("payment success from notification");

    }

    @KafkaListener(topics = "order-complete" ,groupId = "order-complete-group")
    public void consumeCompleted(ShipmentSuccess shipmentSuccess) {
        System.out.println("shipment success from notification");

    }


}
