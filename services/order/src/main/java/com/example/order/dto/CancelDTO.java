package com.example.order.dto;

public class CancelDTO {
    private String orderId;
    public CancelDTO() {
    }
    public CancelDTO(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "CancelDTO{" +
                "orderId='" + orderId + '\'' +
                '}';
    }
}
