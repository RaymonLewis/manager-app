package com.example.manager.repository;

import com.example.manager.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    private final List<Product> products = Collections.synchronizedList(new LinkedList<>());

    @Override
    public List<Product> findAll() {
        return Collections.unmodifiableList(this.products);
    }

    @Override
    public Product save(Product product) {
        product.setId(this.products.size() + 1);
        this.products.add(product);
        return product;
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return this.products.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    @Override
    public void deleteById(int productId) {
        products.removeIf(product -> product.getId() == productId);
    }

}
