package com.simpleDemoApp.simpleDemo.service;

import com.simpleDemoApp.simpleDemo.model.Product;
import com.simpleDemoApp.simpleDemo.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private static final String PRODUCT_IMAGE_URL_PREFIX = "http://localhost:8080/product-images/";
    private static final Path PRODUCT_IMAGE_DIR = Path.of("uploads", "products");

    @Autowired
    ProductRepo repo;

    public Product getProductById(int id) {
        return repo.findById(id)
                .map(this::withImageUrl)
                .orElse(null);
    }

    public List<Product> addProduct(Product prod) {
        repo.save(prod);
        return getProducts();
    }

    public List<Product> addProduct(Product prod, MultipartFile image) throws IOException {
        attachImage(prod, image);
        repo.save(prod);
        return getProducts();
    }

    public List<Product> updateProduct(Product product) {
        repo.save(product);
        return getProducts();
    }

    public List<Product> updateProduct(int id, Product product, MultipartFile image) throws IOException {
        product.setId(id);
        attachImage(product, image);
        repo.save(product);
        return getProducts();
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public List<Product> getProducts() {
        return repo.findAll()
                .stream()
                .map(this::withImageUrl)
                .toList();
    }

    private void attachImage(Product product, MultipartFile image) throws IOException {
        if (image == null || image.isEmpty()) {
            return;
        }

        Files.createDirectories(PRODUCT_IMAGE_DIR);
        String originalName = image.getOriginalFilename() == null ? "product-image" : image.getOriginalFilename();
        String safeName = originalName.replaceAll("[^a-zA-Z0-9._-]", "_");
        String fileName = UUID.randomUUID() + "-" + safeName;
        Path imagePath = PRODUCT_IMAGE_DIR.resolve(fileName);

        Files.copy(image.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
        product.setImageName(originalName);
        product.setImageUrl(PRODUCT_IMAGE_URL_PREFIX + fileName);
    }

    private Product withImageUrl(Product product) {
        if (product.getImageUrl() == null && product.getImageName() != null) {
            product.setImageUrl(PRODUCT_IMAGE_URL_PREFIX + product.getImageName());
        }
        return product;
    }
}
