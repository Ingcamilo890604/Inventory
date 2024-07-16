package com.gestioninventarios.inventarios.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InsufficientQuantityException extends RuntimeException {
    public InsufficientQuantityException(Long id, int quantity) {
        super("Cantidad insuficiente del producto con id: " + id + " para reservar la cantidad: " + quantity);
    }
}
