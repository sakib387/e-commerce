package com.example.order.util;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service",path = "/api/v1/products")
public interface ProductClient {


    @GetMapping("/{product-id}")
    double checkProductAvailability(@PathVariable("product-id") Integer productId);
    @PutMapping("/{product-id}")
    void updateProductQuantity(@PathVariable("product-id") Integer productId, @RequestBody double quantity);

}