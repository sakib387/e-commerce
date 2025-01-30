package com.example.product.controller;


import com.example.product.model.Product;
import com.example.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")

public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Integer> createProduct(
            @RequestBody @Valid Product request
    ) {
        return ResponseEntity.ok(service.createProduct(request));
    }


    @PutMapping("/{product-id}")
    public boolean createProduct(
            @RequestBody   double request, @PathVariable("product-id") Integer productId
    ) {
        return service.updateProduct(request,productId);
    }


    @GetMapping("/{product-id}")
    public double findById(
            @PathVariable("product-id") Integer productId
    ) {
        return ResponseEntity.ok(service.findById(productId)).getBody().getAvailableQuantity();
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}