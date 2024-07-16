package com.gestioninventarios.inventarios.service;


import com.gestioninventarios.inventarios.model.Transaction;
import com.gestioninventarios.inventarios.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public CompletableFuture<List<Transaction>> listTransactions() {
        return CompletableFuture.supplyAsync(() -> transactionRepository.findAll());
    }

}
