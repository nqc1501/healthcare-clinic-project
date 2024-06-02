package com.example.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "t_schedule")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    private Integer id;
    private String doctorId;
    private Integer shiftId;
    private Integer roomId;
}
