package com.example.notification.model;

import com.example.notification.dto.OrderDTO;
import com.example.notification.dto.PaymentDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Builder
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    @Id
    private String id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private OrderDTO orderConfirmation;
    private PaymentDTO paymentConfirmation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public LocalDateTime getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(LocalDateTime notificationDate) {
        this.notificationDate = notificationDate;
    }

    public OrderDTO getOrderConfirmation() {
        return orderConfirmation;
    }

    public void setOrderConfirmation(OrderDTO orderConfirmation) {
        this.orderConfirmation = orderConfirmation;
    }

    public PaymentDTO getPaymentConfirmation() {
        return paymentConfirmation;
    }

    public void setPaymentConfirmation(PaymentDTO paymentConfirmation) {
        this.paymentConfirmation = paymentConfirmation;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", notificationDate=" + notificationDate +
                ", orderConfirmation=" + orderConfirmation +
                ", paymentConfirmation=" + paymentConfirmation +
                '}';
    }
}
