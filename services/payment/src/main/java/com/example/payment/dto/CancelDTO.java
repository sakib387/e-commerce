package com.example.payment.dto;

public class CancelDTO {
    private String orderID;

    public CancelDTO(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
}
