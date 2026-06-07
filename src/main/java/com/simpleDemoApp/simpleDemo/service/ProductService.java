package com.simpleDemoApp.simpleDemo.service;

import com.simpleDemoApp.simpleDemo.model.Product;
import com.simpleDemoApp.simpleDemo.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepo repo;

    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public List<Product> addProduct(Product prod) {
        repo.save(prod);
        return repo.findAll();
    }

    public List<Product> updateProduct(Product product) {
        repo.save(product);
        return repo.findAll();
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public List<Product> getProducts() {
        return repo.findAll();
    }
}
