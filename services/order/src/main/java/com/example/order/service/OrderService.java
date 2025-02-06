package com.example.order.service;

 import com.example.order.dto.OrderStatus;
 import com.example.order.exception.OrderNotFoundException;
import com.example.order.model.Order;

import com.example.order.repository.OrderRepository;
import com.example.order.util.OrderProducer;

 import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {


    private final OrderProducer orderProducer;
    private final OrderRepository orderRepository;

    public OrderService(   OrderProducer orderProducer, OrderRepository orderRepository ) {

        this.orderProducer = orderProducer;
        this.orderRepository = orderRepository;
     }

     public Integer createOrder(Order request) {

            request.setOrderStatus(OrderStatus.CREATED);
            Order order = orderRepository.save(request);
            orderProducer.createMessage(order);
            System.out.println("order Created");
            return order.getId();



    }




    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }


    public Order findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order does not exist for id " + orderId));
    }

    @Transactional
    public String upadateById(Integer orderId,OrderStatus orderStatus) {
        System.out.println(orderStatus);
        Order order=findById(orderId);
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        System.out.println(order);
        return "Updated order ";
    }
}
