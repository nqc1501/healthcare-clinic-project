package com.example.servicemanagement.service.impl;

import com.example.servicemanagement.constant.AppError;
import com.example.servicemanagement.model.Service;
import com.example.servicemanagement.model.Specialist;
import com.example.servicemanagement.payload.AppResponse;
import com.example.servicemanagement.repository.ServiceRepository;
import com.example.servicemanagement.repository.SpecialistRepository;
import com.example.servicemanagement.service.ServiceFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceFunctionImpl implements ServiceFunction {

    @Autowired
    ServiceRepository rService;

    @Autowired
    SpecialistRepository rSpecialist;

    // specialist
    @Override
    public List<Specialist> findAllSpecialist() {
        return rSpecialist.findAll();
    }

    @Override
    public Specialist findSpecialistByServiceId(Integer id) {
        return rSpecialist.findByServiceId(id);
    }

    @Override
    public AppResponse addNewSpecialist(Specialist request) {
        if (rSpecialist.findByName(request.getName()).isPresent()) {
            return new AppResponse("Bản ghi đã tồn tại", false);
        }
        rSpecialist.save(request);
        return new AppResponse("Lưu thành công", true);
    }

    @Override
    public AppResponse updateSpecialist(Specialist request) {
        Specialist specialist = rSpecialist.findById(request.getId()).orElse(null);
        if (specialist == null) {
            return new AppResponse("Bản ghi không tồn tại", false);
        }

        if (request.getListServices().size() != 1) {
            return new AppResponse(AppError.INVALID_DATA, "Dữ liệu không hợp lệ");
        }

        List<Service> listServices = specialist.getListServices();
        if (listServices == null) {
            listServices = new ArrayList<>();
        }

        for (Service s: listServices) {
            if (s.getName()
                    .toLowerCase()
                    .trim()
                    .equals(request.getListServices()
                            .get(0)
                            .getName()
                            .toLowerCase()
                            .trim())) {
                return new AppResponse("Dịch vụ đã tồn tại", false);
            }
        }

        listServices.add(request.getListServices().get(0));
        specialist.setListServices(listServices);
        return new AppResponse(rSpecialist.save(specialist));
    }

    // service
    @Override
    public List<Service> findAllServices() {
        return rService.findAll();
    }

    @Override
    public List<Service> findAllBySpecialist(String specialist) {
        Specialist spe = rSpecialist.findByName(specialist).orElse(null);
        if (spe == null) {
            return null;
        }
        return spe.getListServices();
    }

    @Override
    public AppResponse deleteServiceById(Integer id) {
        Service service = rService.findById(id).orElse(null);

        if (service == null) {
            return new AppResponse("Bản ghi không tồn tại", false);
        }

        rService.delete(service);
        return new AppResponse("Xóa bản ghi thành công", true);
    }

    @Override
    public AppResponse updateService(Service request) {
        if (request == null) {
            return new AppResponse(AppError.INVALID_DATA, "Dữ liệu không hợp lệ");
        }

        Service service = rService.findById(request.getId()).orElse(null);
        if (service == null) {
            return new AppResponse("Bản ghi không tồn tại", false);
        }

        service.setName(request.getName());
        return new AppResponse(rService.save(service));
    }
}
