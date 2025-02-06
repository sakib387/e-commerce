package com.example.product.util;

import com.example.product.dto.CancelDTO;
import com.example.product.dto.OrderDTO;
import com.example.product.dto.OrderLine;
import com.example.product.dto.PaymentDTO;
import com.example.product.service.ProductService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private ProductService productService;
    private KafkaTemplate<Service, OrderDTO> orderDTOKafkaTemplate;
    private KafkaTemplate<Service, CancelDTO> cancelDTOKafkaTemplate;

    public ConsumerService(ProductService productService, KafkaTemplate<Service, OrderDTO> orderDTOKafkaTemplate, KafkaTemplate<Service, CancelDTO> cancelDTOKafkaTemplate) {
        this.productService = productService;
        this.orderDTOKafkaTemplate = orderDTOKafkaTemplate;
        this.cancelDTOKafkaTemplate = cancelDTOKafkaTemplate;
    }

    @KafkaListener(topics = "orderTopic-customer", groupId = "order-product")
    public void consume(OrderDTO orderDTO) {
        System.out.println("cancel order found" + orderDTO);
        boolean available = true;
        for (OrderLine orderLine : orderDTO.getOrderLines()) {
            if (productService.findById(orderLine.getProductId()).getAvailableQuantity() < orderLine.getQuantity())
                available = false;
        }
        if (available) orderDTOKafkaTemplate.send("order-topic-product", orderDTO);
        else cancelDTOKafkaTemplate.send("cancel-order", new CancelDTO(orderDTO.getId()));
    }


    @KafkaListener(topics = "topic-payment", groupId = "update-product")
    public void productUpdate (PaymentDTO paymentDTO) {
        System.out.println("topic-payment found" + paymentDTO.getOrderLine());

        for (OrderLine orderLine:paymentDTO.getOrderLine()){
            productService.updateProduct(orderLine.getQuantity(),orderLine.getProductId());
        }
        System.out.println("Product are updated in inventory..");
    }
}
