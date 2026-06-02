package com.simpleDemoApp.simpleDemo.controller;

import com.simpleDemoApp.simpleDemo.model.Product;
import com.simpleDemoApp.simpleDemo.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable int id) {
        return productService.getProduct(id);
    }

    @GetMapping("/products/name/{name}")
    public Product getProductByName(@PathVariable String name){
        return productService.getProductByName(name);
    }

    @PostMapping("/products")
    public List<Product> addProduct(@RequestBody Product prod){
       return productService.addProduct(prod);
   }
   @PutMapping("/products")
   public List<Product> updateProduct(@RequestBody Product product){
        return  productService.updateProduct(product);
   }
}
