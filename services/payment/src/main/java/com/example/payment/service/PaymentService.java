package com.example.payment.service;

import com.example.payment.model.Payment;
import com.example.payment.repository.PaymentRepository;
import com.example.payment.util.NotificationProducer;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;
    private NotificationProducer notificationProducer;

    public PaymentService(PaymentRepository paymentRepository, NotificationProducer notificationProducer) {
        this.paymentRepository = paymentRepository;
        this.notificationProducer = notificationProducer;
    }

    public Integer createPayment(Payment request) {

        var payment=this.paymentRepository.save(request);
        this.notificationProducer.sendNotification(request);
        System.out.println("notification of payment");
        return  payment.getPaymentId();
    }
}
