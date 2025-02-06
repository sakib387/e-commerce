package com.example.payment.util;


import com.example.payment.dto.CancelDTO;
import com.example.payment.dto.OrderDTO;
import com.example.payment.dto.PaymentDTO;
import com.example.payment.dto.PaymentSuccessDTO;
import com.example.payment.model.Payment;
import com.example.payment.service.PaymentService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConsumerService {

    private PaymentService paymentService;
    private KafkaTemplate<String, PaymentDTO> paymentDTOKafkaTemplate;
    private KafkaTemplate<String, PaymentSuccessDTO> paymentSuccessDTOKafkaTemplate;
    private KafkaTemplate<String, CancelDTO> cancelDTOKafkaTemplate;

    public ConsumerService(PaymentService paymentService, KafkaTemplate<String, PaymentDTO> paymentDTOKafkaTemplate, KafkaTemplate<String, PaymentSuccessDTO> paymentSuccessDTOKafkaTemplate, KafkaTemplate<String, CancelDTO> cancelDTOKafkaTemplate) {
        this.paymentService = paymentService;
        this.paymentDTOKafkaTemplate = paymentDTOKafkaTemplate;
        this.paymentSuccessDTOKafkaTemplate = paymentSuccessDTOKafkaTemplate;
        this.cancelDTOKafkaTemplate = cancelDTOKafkaTemplate;
    }

    @KafkaListener(topics = "order-topic-product", groupId = "order-payment")
    public void consume(OrderDTO orderDTO) {
        Payment payment = new Payment();
        payment.setAmount(orderDTO.getTotalAmount());
        payment.setPaymentMethod(orderDTO.getPaymentMethod());
        payment.setOrderId(orderDTO.getId());
        payment.setCustomerId(orderDTO.getCustomerId());
        payment.setCreatedDate(LocalDateTime.parse(orderDTO.getCreatedDate()));
        payment.setLastModifiedDate(LocalDateTime.parse(orderDTO.getLastModifiedDate()));

        payment = paymentService.createPayment(payment);

        if (payment.getPaymentId() != null) {
            paymentDTOKafkaTemplate.send("topic-payment", new PaymentDTO(payment.getPaymentId(), payment.getAmount(), payment.getPaymentMethod(), orderDTO.getOrderLines(), payment.getOrderId(), payment.getCustomerId(), payment.getCreatedDate().toString(), payment.getLastModifiedDate().toString()));
            paymentSuccessDTOKafkaTemplate.send("payment-success-1", new PaymentSuccessDTO(payment.getOrderId()));
            System.out.println("payment done " + payment.getOrderId());
        } else {
            cancelDTOKafkaTemplate.send("cancel-order", new CancelDTO(orderDTO.getId()));
        }
    }
}
