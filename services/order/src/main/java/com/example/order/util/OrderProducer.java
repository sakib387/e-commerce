package com.example.order.util;

import com.example.order.dto.OrderDTO;
import com.example.order.model.Order;
import org.springframework.messaging.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private KafkaTemplate<String, OrderDTO> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, OrderDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void createMessage(Order order) {


        OrderDTO orderDTO = new OrderDTO(order.getId(),order.getTotalAmount(),order.getPaymentMethod(),order.getCustomerId(),
                order.getOrderLines(),order.getCreatedDate().toString(),order.getLastModifiedDate().toString());
        Message<OrderDTO> message = MessageBuilder.withPayload(orderDTO)
                .setHeader(KafkaHeaders.TOPIC, "orderTopic-2")
                .build();


        kafkaTemplate.send(message);

        System.out.println("Order message sent to Kafka topic: order-topic");
    }
}
