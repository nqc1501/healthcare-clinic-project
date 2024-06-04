package com.example.healthcareservice.model.item;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "t_medication")
@Entity
public class Medication extends Item {

    private String composition; // thành phần
    private String userPersona; // đối tượng sử dụng
    private String caution; // thận trọng
    private String storage; // bảo quản
}
