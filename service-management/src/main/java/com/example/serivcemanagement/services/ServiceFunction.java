package com.example.serivcemanagement.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceFunction {

    List<com.example.serivcemanagement.models.Service> findAllBySpecialist(String specialist);
}
