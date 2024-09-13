package com.example.manager.service;

import com.example.manager.controller.payload.NewProductPayload;
import com.example.manager.controller.payload.UpdateProductPayload;
import com.example.manager.entity.Product;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    List<Product> findAllProducts();

    Product createProduct(String title, String details);

    Optional<Product> findProductById(int id);

    void updateProduct(int productId, UpdateProductPayload payload);

    void deleteProduct(int productId);
}
