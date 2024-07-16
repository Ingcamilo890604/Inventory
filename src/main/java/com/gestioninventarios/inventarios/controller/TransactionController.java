package com.gestioninventarios.inventarios.controller;


import com.gestioninventarios.inventarios.model.Transaction;
import com.gestioninventarios.inventarios.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(value = "getAll")
    public CompletableFuture<ResponseEntity<List<Transaction>>> listarProductos() {
        return transactionService.listTransactions()
                .thenApply(ResponseEntity::ok);
    }
}
