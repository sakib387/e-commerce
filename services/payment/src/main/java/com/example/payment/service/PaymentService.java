package com.example.payment.service;

import com.example.payment.model.Payment;
import com.example.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository ) {
        this.paymentRepository = paymentRepository;
     }

    public Payment createPayment(Payment request) {

        var payment=this.paymentRepository.save(request);
         System.out.println("notification of payment");
        return  payment ;
    }
}
