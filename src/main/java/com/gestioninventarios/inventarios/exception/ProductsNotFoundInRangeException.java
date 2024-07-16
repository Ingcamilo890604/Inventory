package com.gestioninventarios.inventarios.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductsNotFoundInRangeException extends RuntimeException {
    public ProductsNotFoundInRangeException(Double precioMin, Double precioMax) {
        super("No se encontraron productos en el rango de precios: " + precioMin + " - " + precioMax);
    }
}
