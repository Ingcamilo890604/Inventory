package com.gestioninventarios.inventarios.service;

import com.gestioninventarios.inventarios.model.Product;
import com.gestioninventarios.inventarios.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final ProductRepository productRepository;

    public CompletableFuture<String> generateInventoryReport() {
        return CompletableFuture.supplyAsync(() -> {
            StringBuilder report = new StringBuilder();
            report.append("Reporte de Inventario:\n");

            List<Product> allProducts = productRepository.findAll();

            report.append("\nProductos con baja cantidad:\n");
            allProducts.stream()
                    .filter(product -> product.getQuantity() < 10)
                    .forEach(product -> report.append(product.getName()).append(" - Cantidad: ").append(product.getQuantity()).append("\n"));

            report.append("\nProductos más vendidos:\n");
            allProducts.stream()
                    .sorted((p1, p2) -> Integer.compare(p2.getReservedQuantity(), p1.getReservedQuantity()))
                    .limit(10)
                    .forEach(product -> report.append(product.getName()).append(" - Cantidad Vendida: ").append(product.getReservedQuantity()).append("\n"));

            return report.toString();
        });
    }
}
