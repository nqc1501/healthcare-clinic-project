package com.example.userservice.services.nurse;

import com.example.userservice.models.Nurse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NurseService {
    Nurse findNurseById(String id);

    List<Nurse> findAllNurses();
}
