package com.example.notification.service;


import com.example.payment.dto.PaymentDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.example.order.dto.OrderDTO;


@Service
public class NotificationsConsumer {


    @KafkaListener(topics = "paymentTopic-2" ,groupId = "order-group")
    public void consume(PaymentDTO payment) {
        System.out.println("Received payment: " + payment);
    }

    @KafkaListener(topics = "orderTopic-2" ,groupId = "order-group")
    public void consume(OrderDTO order) {
        System.out.println("Received Order: " + order);
    }
}
