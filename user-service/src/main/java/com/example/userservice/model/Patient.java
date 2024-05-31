package com.example.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "t_patient")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends User {

    private Integer age;
    private String healthInsuranceCode;
    private String status;
}
