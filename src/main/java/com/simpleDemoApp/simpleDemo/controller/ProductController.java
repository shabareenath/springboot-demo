package com.simpleDemoApp.simpleDemo.controller;

import com.simpleDemoApp.simpleDemo.model.Product;
import com.simpleDemoApp.simpleDemo.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"/products", "/products "})
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping(value = {"/products", "/products "}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> addProduct(@RequestBody Product prod) {
        return productService.addProduct(prod);
    }

    @PostMapping({"/products", "/products "})
    public List<Product> addProductWithImageFields(@RequestParam String name,
                                                   @RequestParam int price,
                                                   @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        return productService.addProduct(product, image);
    }

    @PutMapping("/products")
    public List<Product> updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @PutMapping("/products/{id}")
    public List<Product> updateProductWithImageFields(@PathVariable int id,
                                                      @RequestParam String name,
                                                      @RequestParam int price,
                                                      @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        return productService.updateProduct(id, product, image);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }
}
