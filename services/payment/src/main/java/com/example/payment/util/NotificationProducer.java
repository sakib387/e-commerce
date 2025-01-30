package com.example.payment.util;

import com.example.payment.dto.PaymentDTO;
import com.example.payment.model.Payment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
public class NotificationProducer {
    private final KafkaTemplate<String, PaymentDTO> kafkaTemplate;

    public NotificationProducer(KafkaTemplate<String, PaymentDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendNotification(Payment request) {
       PaymentDTO paymentDTO=new PaymentDTO(request.getPaymentId(),request.getAmount(),
                request.getPaymentMethod(),request.getOrderId(),request.getCustomerId(),request.
                getCreatedDate().toString(),request.getLastModifiedDate().toString());
        Message<PaymentDTO> message = MessageBuilder
                .withPayload(paymentDTO)
                .setHeader(TOPIC, "paymentTopic-2")
                .build();

        kafkaTemplate.send(message);
    }
}
