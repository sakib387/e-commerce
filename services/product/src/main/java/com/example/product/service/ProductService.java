package com.example.product.service;

import com.example.product.exception.ProductNotFoundException;
import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Integer createProduct(
            Product request
    ) {
        System.out.println(request);
        var product = this.repository.save(request);
        return repository.save(product).getId();
    }

    public Product findById(Integer productId) {
        return this.repository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found."));
    }

    public List<Product> findAll() {
        return this.repository.findAll();
    }


    public boolean updateProduct(double request, Integer productId) {
         Product product = repository.findById(productId).orElse(null);

        if (product != null) {
            // Update the product's quantity or other fields
            product.setAvailableQuantity(product.getAvailableQuantity()-request);
            repository.save(product);
            return true;
        }

        return false;
    }

}
