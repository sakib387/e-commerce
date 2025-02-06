package com.example.notification.dto;

public class PaymentSuccessDTO {
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public PaymentSuccessDTO(String orderId) {
        this.orderId = orderId;
    }

    public PaymentSuccessDTO() {
    }
}
