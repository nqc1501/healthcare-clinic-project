package com.example.supplyservice.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {

    @Id
    private String id;
    private String name;
    private String type;
    private String howToUse;
    private BigDecimal price;
    private Integer quantity;
}
