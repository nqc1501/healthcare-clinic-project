package com.example.serivcemanagement.services.impl;

import com.example.serivcemanagement.models.Service;
import com.example.serivcemanagement.models.Specialist;
import com.example.serivcemanagement.repositories.ServiceRepository;
import com.example.serivcemanagement.repositories.SpecialistRepository;
import com.example.serivcemanagement.services.ServiceFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceFunctionImpl implements ServiceFunction {

    @Autowired
    ServiceRepository rService;

    @Autowired
    SpecialistRepository rSpecialist;

    @Override
    public List<Service> findAllBySpecialist(String specialist) {
        Specialist spe = rSpecialist.findByName(specialist).orElse(null);
        if (spe == null) {
            return null;
        }
        return spe.getListService();
    }
}
