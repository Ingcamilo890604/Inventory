package com.gestioninventarios.inventarios.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InsufficientReservedQuantityException extends RuntimeException {
    public InsufficientReservedQuantityException(Long id, int quantity) {
        super("Cantidad insuficiente reservada del producto con id: " + id + " para liberar la cantidad: " + quantity);
    }
}
