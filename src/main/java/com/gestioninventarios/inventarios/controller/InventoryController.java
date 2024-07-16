package com.gestioninventarios.inventarios.controller;

import com.gestioninventarios.inventarios.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/inventario")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/report")
    public CompletableFuture<ResponseEntity<String>> generateInventoryReport() {
        return inventoryService.generateInventoryReport()
                .thenApply(ResponseEntity::ok);
    }
}
