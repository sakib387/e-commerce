package com.example.customer.util;

import com.example.customer.dto.CancelDTO;
import com.example.customer.dto.OrderDTO;
import com.example.customer.model.Customer;
import com.example.customer.service.CustomerService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private CustomerService customerService;
    private final KafkaTemplate<String,OrderDTO> orderDTOKafkaTemplate;
    private final KafkaTemplate<String, CancelDTO> cancelDTOKafkaTemplate;

    public ConsumerService(CustomerService customerService, KafkaTemplate<String, OrderDTO> orderDTOKafkaTemplate, KafkaTemplate<String, CancelDTO> cancelDTOKafkaTemplate) {
        this.customerService = customerService;
        this.orderDTOKafkaTemplate = orderDTOKafkaTemplate;
        this.cancelDTOKafkaTemplate = cancelDTOKafkaTemplate;
    }

    @KafkaListener(topics = "orderTopic-2" ,groupId = "order-group-customer")
    public void consume(OrderDTO order) {
        System.out.println("Received Order: " + order.getCustomerId());

        Customer customer=customerService.findById(order.getCustomerId() );
        if(customer!=null){
            orderDTOKafkaTemplate.send("orderTopic-customer",order);
            System.out.println("Order is in pro...");
        }
        else{
            CancelDTO cancelDTO=new CancelDTO(order.getId());
            cancelDTOKafkaTemplate.send("cancel-order",cancelDTO);
            System.out.println("order is canceled ");
        }


    }
}
