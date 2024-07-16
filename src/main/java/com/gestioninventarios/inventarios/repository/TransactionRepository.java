package com.gestioninventarios.inventarios.repository;

import com.gestioninventarios.inventarios.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

}
