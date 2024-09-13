package com.example.manager.service;

import com.example.manager.controller.payload.NewProductPayload;
import com.example.manager.controller.payload.UpdateProductPayload;
import com.example.manager.entity.Product;
import com.example.manager.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product createProduct(String title, String details) {
        Product product = new Product(null, title, details);
        return this.productRepository.save(product);
    }

    @Override
    public Optional<Product> findProductById(int id) {
        Optional<Product> product = this.productRepository.findById(id);
        return product;
    }

    @Override
    public void updateProduct(int productId, UpdateProductPayload payload) {
        this.productRepository.findById(productId)
                .ifPresentOrElse(product -> {
                    product.setTitle(payload.title());
                    product.setDetails(payload.details());
                }, () -> {
                    throw new NoSuchElementException("Product not found with id: " + productId);
                });
    }

    @Override
    public void deleteProduct(int productId) {
        this.productRepository.deleteById(productId);
    }

}