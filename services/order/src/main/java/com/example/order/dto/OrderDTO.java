package com.example.order.dto;

import com.example.order.model.OrderLine;


import java.math.BigDecimal;
import java.util.List;

public class OrderDTO  {

    private Integer id;

    private BigDecimal totalAmount;


    private PaymentMethod paymentMethod;

    private String customerId;


    private List<OrderLine> orderLines;

    private String createdDate;

    private String lastModifiedDate;

    public OrderDTO(Integer id, BigDecimal totalAmount, PaymentMethod paymentMethod, String customerId, List<OrderLine> orderLines, String createdDate, String lastModifiedDate) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.customerId = customerId;
        this.orderLines = orderLines;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}