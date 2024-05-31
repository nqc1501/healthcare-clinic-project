package com.example.supplyservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "t_supply")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Supply extends Item {

    private Double length;
    private Double width;
}
