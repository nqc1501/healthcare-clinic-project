package com.example.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "t_nurse")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Nurse extends User {

    private String degree;
}
