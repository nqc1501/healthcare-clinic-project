package com.example.facilityscheduling.service;

import com.example.facilityscheduling.model.Specialty;

import java.util.List;

public interface SpecialtyService {

    List<Specialty> getAllSpecialty();

    Specialty getById(Integer id);
}
