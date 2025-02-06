package com.example.customer.dto;

public class CancelDTO {
    private String orderId;

    public CancelDTO(String orderId) {
        this.orderId = orderId;
    }

    public CancelDTO() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
