package com.example.order.service;

import com.example.order.exception.CustomerNotFound;
import com.example.order.exception.OrderCreationException;
import com.example.order.exception.OrderNotFoundException;
import com.example.order.model.Order;

import com.example.order.model.OrderLine;
import com.example.order.dto.PaymentRequest;
import com.example.order.repository.OrderRepository;
import com.example.order.util.CustomerClient;
import com.example.order.util.OrderProducer;
import com.example.order.util.PaymentClient;
import com.example.order.util.ProductClient;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderProducer orderProducer;
    private final OrderRepository orderRepository;
    private final PaymentClient paymentClient;

    public OrderService(CustomerClient customerClient, ProductClient productClient, OrderProducer orderProducer, OrderRepository orderRepository, PaymentClient paymentClient) {
        this.customerClient = customerClient;
        this.productClient = productClient;
        this.orderProducer = orderProducer;
        this.orderRepository = orderRepository;
        this.paymentClient = paymentClient;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer createOrder(Order request) {

        System.out.println(request);
        boolean productAvailable = true;
        Object customer = this.customerClient.findCustomerById(request.getCustomerId().toString())
                .orElseThrow(() -> new CustomerNotFound("Cannot create order:: No customer exists with the provided ID"));
        // var purchasedProducts = productClient.purchaseProducts(request.getOrderLines());
        for (OrderLine orderLine : request.getOrderLines()) {
            double qn = productClient.checkProductAvailability(orderLine.getProductId());

            if (qn < orderLine.getQuantity()) productAvailable = false;
        }


        var paymentRequest = new PaymentRequest(
                request.getTotalAmount(),
                request.getPaymentMethod(),
                request.getId(),
                request.getCustomerId().toString()
        );

        Object payment = this.paymentClient.requestOrderPayment(paymentRequest);
        if (payment == null) throw new OrderCreationException("Please complete payment first ");

        if (productAvailable && customer != null) {

            for (OrderLine orderLine : request.getOrderLines()) {
                productClient.updateProductQuantity(orderLine.getProductId(), orderLine.getQuantity());
            }
            Order order = orderRepository.save(request);
            orderProducer.createMessage(order);
            System.out.println("order done");
            return order.getId();
        } else {
            throw new OrderCreationException("Don't have Available product or Payment not completed");
        }


    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }


    public Order findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order does not exist for id " + orderId));
    }

}
