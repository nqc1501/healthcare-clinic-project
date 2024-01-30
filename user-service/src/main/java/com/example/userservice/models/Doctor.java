package com.example.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "t_doctor")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends User {

    private String specialist;
    private String degree;
}
