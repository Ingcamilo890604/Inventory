package com.gestioninventarios.inventarios.controller;

import com.gestioninventarios.inventarios.dto.ProductDTO;
import com.gestioninventarios.inventarios.model.Product;
import com.gestioninventarios.inventarios.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/productos")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping(value = "create")
    public CompletableFuture<ResponseEntity<Product>> addProduct(@RequestBody ProductDTO product) {
        return productService.addProduct(product)
                .thenApply(ResponseEntity::ok);
    }

    @PutMapping(value = "update/{id}")
    public CompletableFuture<ResponseEntity<Product>> updateProduct(@PathVariable Long id, @RequestParam int quantity, @RequestParam double price) {
        return productService.updateProduct(id, quantity, price)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping(value = "getAll")
    public CompletableFuture<ResponseEntity<List<Product>>> listProduct() {
        return productService.listProducts()
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/categoria/{category}")
    public CompletableFuture<ResponseEntity<List<Product>>> findByCategory(@PathVariable String category) {
        return productService.findByCategory(category)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/precio")
    public CompletableFuture<ResponseEntity<List<Product>>> findByRangePrice(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
        return productService.findByRangePrice(minPrice, maxPrice)
                .thenApply(ResponseEntity::ok);
    }

    @DeleteMapping("delete/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id)
                .thenApply(v -> ResponseEntity.ok().build());
    }
    @PutMapping("/reserve/{id}")
    public CompletableFuture<ResponseEntity<Product>> reserveProduct(@PathVariable Long id, @RequestParam int quantity) {
        return productService.reserveProduct(id, quantity)
                .thenApply(ResponseEntity::ok);

    }

    @PutMapping("/release/{id}")
    public CompletableFuture<ResponseEntity<Product>> releaseProduct(@PathVariable Long id, @RequestParam int quantity) {
        return productService.releaseProduct(id, quantity)
                .thenApply(ResponseEntity::ok);
    }
    @PutMapping("/restock/{id}")
    public CompletableFuture<ResponseEntity<Product>> restockProduct(@PathVariable Long id, @RequestParam int restockQuantity) {
        return productService.restockProduct(id, restockQuantity)
                .thenApply(ResponseEntity::ok);
    }
}
