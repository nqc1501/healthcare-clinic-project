package com.example.userservice.services.nurse.impl;

import com.example.userservice.models.Nurse;
import com.example.userservice.repositories.NurseRepository;
import com.example.userservice.services.nurse.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class NurseServiceImpl implements NurseService {

    @Autowired
    NurseRepository nurseRepository;

    @Override
    public Nurse findNurseById(String id) {
        Optional<Nurse> optional = nurseRepository.findById(id);
        return optional.orElseGet(() -> null);
    }

    @Override
    public List<Nurse> findAllNurses() {
        return nurseRepository.findAll();
    }
}
