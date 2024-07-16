package com.gestioninventarios.inventarios.model;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private Integer quantity;
    private int reservedQuantity;
    private Double price;
    private int restockThreshold;

    @Version
    private int version;
}
