package com.gestioninventarios.inventarios.observer;

import com.gestioninventarios.inventarios.model.Product;
import com.gestioninventarios.inventarios.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AutoRestockObserver implements InventoryObserver{
    private final ProductRepository productRepository;

    @Override
    public void notifyChanges(Product product, String operation) {
        if (product.getQuantity() < product.getRestockThreshold()) {
            int restockAmount = product.getRestockThreshold() * 2;
            product.setQuantity(product.getQuantity() + restockAmount);
            productRepository.save(product);
            System.out.println("Producto reabastecido automáticamente: " + product.getName());
        }
    }
}
