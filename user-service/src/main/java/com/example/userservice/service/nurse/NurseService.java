package com.example.userservice.service.nurse;

import com.example.userservice.model.Nurse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NurseService {
    Nurse findNurseById(String id);

    List<Nurse> findAllNurses();
}
