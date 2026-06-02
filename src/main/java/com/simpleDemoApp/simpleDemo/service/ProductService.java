package com.simpleDemoApp.simpleDemo.service;

import com.simpleDemoApp.simpleDemo.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    List<Product> products = new ArrayList<>(Arrays.asList(new Product(1,"vivo",100000),
                                           new Product(2,"mi",50000),
                                           new Product(3,"iphone",40000)));
    public List<Product> getProducts(){
        return products;
    }

    public Product getProduct(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Product getProductByName(String name) {
        return products.stream()
                .filter(p -> Objects.equals(p.getName(), name))
                .findFirst()
                .orElse(null);
    }

    public List<Product> addProduct(Product prod){
        products.add(prod);
        System.out.println("prod added!");
        return products;
    }

    public List<Product>updateProduct(Product product){
    int index = 0;
    for(int i = 0;i<products.size();i++){
        if(products.get(i).getId()==product.getId()){
            index = i;
        }
    }
    products.set(index,product);
    return products;
     }
}

