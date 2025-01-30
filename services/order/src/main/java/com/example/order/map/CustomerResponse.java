package com.example.order.map;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {

}