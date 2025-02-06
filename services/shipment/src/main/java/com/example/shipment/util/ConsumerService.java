package com.example.shipment.util;

import com.example.shipment.dto.PaymentDTO;
import com.example.shipment.dto.ShipmentSuccess;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private KafkaTemplate<String, ShipmentSuccess>kafkaTemplate;

    public ConsumerService(KafkaTemplate<String, ShipmentSuccess> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "topic-payment",groupId="shipment-product")
    public void consume(PaymentDTO paymentDTO){
        System.out.println("Order received  by  customer id "+paymentDTO.getCustomerId());
        kafkaTemplate.send("order-complete",new ShipmentSuccess(paymentDTO.getOrderId()));
    }
}
