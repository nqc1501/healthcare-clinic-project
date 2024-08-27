package com.example.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "t_doctor")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends User {

    private Integer specialtyId;
    private String degree;
}
