package com.gestioninventarios.inventarios.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SufficientProductQuantityException extends RuntimeException {
    public SufficientProductQuantityException(Long id) {
        super("La cantidad del producto con id: " + id + " es suficiente, no es necesario reabastecer");
    }
}