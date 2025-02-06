package com.example.shipment.dto;

public class ShipmentSuccess {
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public ShipmentSuccess(String orderId) {
        this.orderId = orderId;
    }
}
