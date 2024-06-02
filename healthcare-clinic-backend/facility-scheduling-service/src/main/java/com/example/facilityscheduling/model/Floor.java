package com.example.facilityscheduling.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Table(name = "t_floor")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Floor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer specialtyId;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "floor_id", referencedColumnName = "id")
    private List<Room> listRooms;
}
