package com.example.facilityscheduling.service.impl;

import com.example.facilityscheduling.model.Specialty;
import com.example.facilityscheduling.repository.SpecialtyRepository;
import com.example.facilityscheduling.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    @Autowired
    SpecialtyRepository rSpecialty;

    @Override
    public List<Specialty> getAllSpecialty() {
        return rSpecialty.findAll();
    }

    @Override
    public Specialty getById(Integer id) {

        if (ObjectUtils.isEmpty(id)) {
            return null;
        }

        return rSpecialty.findById(id).orElse(null);
    }
}
