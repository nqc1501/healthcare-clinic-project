package com.example.supplyservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Table(name = "t_medication")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Medication extends Item {
    private String composition; // thành phần
    private String userPersona; // đối tượng sử dụng
    private String caution; // thận trọng
    private String storage; // bảo quản
}
