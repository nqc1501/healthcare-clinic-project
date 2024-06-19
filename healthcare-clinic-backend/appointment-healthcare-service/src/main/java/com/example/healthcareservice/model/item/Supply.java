package com.example.healthcareservice.model.item;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "t_supply")
@Entity
public class Supply extends Item {

    private Integer roomId;
    private Double length;
    private Double width;
}
