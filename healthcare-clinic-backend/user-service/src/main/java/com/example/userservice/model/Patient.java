package com.example.userservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "t_patient")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends User {

    private Integer age;
    @Column(unique = true)
    private String healthInsuranceCode;
    private String status;
}
