package com.example.order.util;

import com.example.order.dto.CancelDTO;
import com.example.order.dto.OrderStatus;
import com.example.order.dto.PaymentSuccessDTO;
import com.example.order.dto.ShipmentSuccess;
import com.example.order.service.OrderService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private   OrderService orderService;

    public ConsumerService(OrderService orderService) {
        this.orderService = orderService;
    }


    @KafkaListener(topics = "cancel-order" ,groupId = "order-cancel")
    public void consume(CancelDTO cancelDTO) {
        System.out.println("cancel order found"+cancelDTO);
       orderService.upadateById(Integer.parseInt(cancelDTO.getOrderId()), OrderStatus.CANCELED) ;
    }

    @KafkaListener(topics = "order-complete" ,groupId = "order-complete-group-01")
    public void consumeCompleted(ShipmentSuccess shipmentSuccess) {
        System.out.println("shipment success from notification");
        orderService.upadateById(Integer.parseInt(shipmentSuccess.getOrderId()), OrderStatus.DONE) ;

    }

    @KafkaListener(topics = "payment-success-1" ,groupId = "payment-complete-group-01")
    public void paymentCompleted(PaymentSuccessDTO paymentSuccessDTO) {
        System.out.println("payment success from notification");

        orderService.upadateById(Integer.parseInt(paymentSuccessDTO.getOrderId()), OrderStatus.SHIPPING) ;

    }

}
