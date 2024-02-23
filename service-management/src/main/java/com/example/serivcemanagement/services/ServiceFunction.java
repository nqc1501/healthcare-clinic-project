package com.example.serivcemanagement.services;

import com.example.serivcemanagement.dto.req.SpecialistRequest;
import com.example.serivcemanagement.models.Specialist;
import com.example.serivcemanagement.payload.AppResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
public interface ServiceFunction {

    // specialist
    List<Specialist> findAllSpecialist();

    Specialist findSpecialistByServiceId(Integer id);

    AppResponse addNewSpecialist(Specialist request);

    AppResponse updateSpecialist(Specialist request);

    // service
    List<com.example.serivcemanagement.models.Service> findAllBySpecialist(String specialist);

    List<com.example.serivcemanagement.models.Service> findAllServices();

    AppResponse deleteServiceById(Integer id);

    AppResponse updateService(com.example.serivcemanagement.models.Service request);
}
