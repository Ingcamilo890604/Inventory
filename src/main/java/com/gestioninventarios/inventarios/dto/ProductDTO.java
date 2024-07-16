package com.gestioninventarios.inventarios.dto;

public record ProductDTO(Long id, String name, String category, int quantity, Double price, int restockThreshold ) {
}
