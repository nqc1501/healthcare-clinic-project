package com.example.servicemanagement.service;

import com.example.servicemanagement.model.Specialist;
import com.example.servicemanagement.payload.AppResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceFunction {

    // specialist
    List<Specialist> findAllSpecialist();

    Specialist findSpecialistByServiceId(Integer id);

    AppResponse addNewSpecialist(Specialist request);

    AppResponse updateSpecialist(Specialist request);

    // service
    List<com.example.servicemanagement.model.Service> findAllBySpecialist(String specialist);

    List<com.example.servicemanagement.model.Service> findAllServices();

    AppResponse deleteServiceById(Integer id);

    AppResponse updateService(com.example.servicemanagement.model.Service request);
}
