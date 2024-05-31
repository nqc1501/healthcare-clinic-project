package com.example.scheduleservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Table(name = "t_shift")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date startDate;
    private Date endDate;
    private String dayOfWeek;
    private String startTime;
    private String endTime;
    private Integer quantity;
    private Integer quantityRegistered;
    private Boolean isSelected;
}
