package com.example.userservice.service.nurse.impl;

import com.example.userservice.model.Nurse;
import com.example.userservice.repository.NurseRepository;
import com.example.userservice.service.nurse.NurseService;
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
