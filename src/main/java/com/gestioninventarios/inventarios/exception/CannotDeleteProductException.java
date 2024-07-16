package com.gestioninventarios.inventarios.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CannotDeleteProductException extends RuntimeException {
    public CannotDeleteProductException(Long id) {
        super("No se puede eliminar el producto con id: " + id + " porque su cantidad es mayor a cero");
    }
}