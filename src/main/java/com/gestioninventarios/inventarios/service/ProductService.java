package com.gestioninventarios.inventarios.service;

import com.gestioninventarios.inventarios.dto.ProductDTO;
import com.gestioninventarios.inventarios.exception.CannotDeleteProductException;
import com.gestioninventarios.inventarios.exception.CategoryNotFoundException;
import com.gestioninventarios.inventarios.exception.InsufficientQuantityException;
import com.gestioninventarios.inventarios.exception.InsufficientReservedQuantityException;
import com.gestioninventarios.inventarios.exception.ProductsNotFoundInRangeException;
import com.gestioninventarios.inventarios.exception.ProductNotFoundException;
import com.gestioninventarios.inventarios.exception.SufficientProductQuantityException;
import com.gestioninventarios.inventarios.model.Product;
import com.gestioninventarios.inventarios.observer.InventoryObserver;
import com.gestioninventarios.inventarios.observer.InventorySubject;
import com.gestioninventarios.inventarios.observer.InventorySubjectImpl;
import com.gestioninventarios.inventarios.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductService implements InventorySubject  {
    @Autowired
    private ProductRepository productRepository;

    private final InventorySubjectImpl subjectImpl = new InventorySubjectImpl();

    @Autowired
    public ProductService(List<InventoryObserver> observers) {
        observers.forEach(observer -> {
            if (!subjectImpl.getObservers().contains(observer)) {
                addObserver(observer);
            }
        });
    }

    @Override
    public void addObserver(InventoryObserver observer) {
        if (!subjectImpl.getObservers().contains(observer)) {
            subjectImpl.addObserver(observer);
        }
    }
    @Override
    public void deleteObserver(InventoryObserver observer) {
        subjectImpl.deleteObserver(observer);
    }

    @Override
    public void notifyObservers(Product product, String operation) {
        subjectImpl.notifyObservers(product, operation);
    }

    @Transactional
    public CompletableFuture<Product> addProduct(ProductDTO productDTO) {
        return CompletableFuture.supplyAsync(() -> {
            Product product = new Product();
            product.setName(productDTO.name());
            product.setCategory(productDTO.category());
            product.setQuantity(productDTO.quantity());
            product.setPrice(productDTO.price());
            product.setRestockThreshold(productDTO.restockThreshold());
            Product savedProduct = productRepository.save(product);
            notifyObservers(savedProduct, "agregar");
            return savedProduct;
        });
    }

    @Transactional
    public CompletableFuture<Product> updateProduct(Long id, int quantity, Double price) {
        return CompletableFuture.supplyAsync(() -> {
            Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
            product.setQuantity(quantity);
            product.setPrice(price);
            Product updatedProduct = productRepository.save(product);
            notifyObservers(updatedProduct, "actualizar");
            return updatedProduct;
        });
    }

    public CompletableFuture<List<Product>> listProducts() {
        return CompletableFuture.supplyAsync(() -> productRepository.findAll());
    }

    public CompletableFuture<List<Product>> findByCategory(String category) {
        return CompletableFuture.supplyAsync(() -> productRepository.findByCategory(category))
                .thenApply(products -> {
                    if (products == null || products.isEmpty()) {
                        throw new CategoryNotFoundException(category);
                    }
                    return products;
                });
    }

    public CompletableFuture<List<Product>> findByRangePrice(Double precioMin, Double precioMax) {
        return CompletableFuture.supplyAsync(() -> productRepository.findByPriceBetween(precioMin, precioMax))
                .thenApply(products -> {
                    if (products == null || products.isEmpty()) {
                        throw new ProductsNotFoundInRangeException(precioMin, precioMax);
                    }
                    return products;
                });
    }

    @Transactional
    public CompletableFuture<Void> deleteProduct(Long id) {
        return CompletableFuture.runAsync(() -> {
            Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
            if (product.getQuantity() == 0) {
                productRepository.delete(product);
                notifyObservers(product, "borrar");
            } else {
                throw new CannotDeleteProductException(id);
            }
        });
    }

    @Transactional
    public CompletableFuture<Product> reserveProduct(Long id, int quantity) {
        return CompletableFuture.supplyAsync(() -> {
            Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
            if (product.getQuantity() >= quantity) {
                product.setQuantity(product.getQuantity() - quantity);
                product.setReservedQuantity(product.getReservedQuantity() + quantity);
                Product reservedProduct = productRepository.save(product);
                notifyObservers(reservedProduct, "reservar");
                return reservedProduct;
            } else {
                throw new InsufficientQuantityException(id,quantity);
            }
        });
    }

    @Transactional
    public CompletableFuture<Product> releaseProduct(Long id, int quantity) {
        return CompletableFuture.supplyAsync(() -> {
            Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
            if (product.getReservedQuantity() >= quantity) {
                product.setQuantity(product.getQuantity() + quantity);
                product.setReservedQuantity(product.getReservedQuantity() - quantity);
                Product releasedProduct = productRepository.save(product);
                notifyObservers(releasedProduct, "liberar");
                return releasedProduct;
            } else {
                throw new InsufficientReservedQuantityException(id, quantity);
            }
        });
    }

    @Transactional
    public CompletableFuture<Product> restockProduct(Long id, int restockQuantity) {
        return CompletableFuture.supplyAsync(() -> {
            Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
            if (product.getQuantity() < 10) {
                product.setQuantity(product.getQuantity() + restockQuantity);
                Product restockedProduct = productRepository.save(product);
                notifyObservers(restockedProduct, "reabastecer");
                return restockedProduct;
            } else {
                throw new SufficientProductQuantityException(id);
            }
        });
    }
}
