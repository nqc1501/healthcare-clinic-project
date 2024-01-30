package com.example.supplyservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "t_medication")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Medication extends Item {
}
