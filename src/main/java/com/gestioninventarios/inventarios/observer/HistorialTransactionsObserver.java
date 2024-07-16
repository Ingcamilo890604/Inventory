package com.gestioninventarios.inventarios.observer;

import com.gestioninventarios.inventarios.model.Product;
import com.gestioninventarios.inventarios.model.Transaction;
import com.gestioninventarios.inventarios.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class HistorialTransactionsObserver implements InventoryObserver{
    private final TransactionRepository transactionRepository;
    @Override
    public void notifyChanges(Product product, String operacion) {
                Transaction transaction = Transaction.builder()
                .operationType(operacion)
                .date(LocalDateTime.now())
                .productId(product.getId())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .build();
        transactionRepository.save(transaction);
        System.out.println("Registro de transacción: " + operacion + " producto: " + product.getName());
    }
}
