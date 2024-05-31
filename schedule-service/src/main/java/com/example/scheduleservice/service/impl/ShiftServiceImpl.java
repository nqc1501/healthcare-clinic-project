package com.example.scheduleservice.service.impl;

import com.example.scheduleservice.model.Shift;
import com.example.scheduleservice.model.UserShift;
import com.example.scheduleservice.payload.AppResponse;
import com.example.scheduleservice.repository.ShiftRepository;
import com.example.scheduleservice.repository.UserShiftRepository;
import com.example.scheduleservice.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ShiftServiceImpl implements ShiftService {

    @Autowired
    ShiftRepository rShift;

    @Autowired
    UserShiftRepository rUserShift;

    @Override
    public List<Shift> findAllShift() {
        return rShift.findAll();
    }

    @Override
    public List<Shift> findByUserId(String userId) {

        List<UserShift> listUserShift = rUserShift.findAllByUserId(userId);
        List<Shift> listShift = new ArrayList<>();

        for (UserShift u: listUserShift) {
            Optional<Shift> shiftOpt = rShift.findById(u.getShiftId());
            shiftOpt.ifPresent(listShift::add);
        }

        return listShift;
    }

    @Override
    public AppResponse addNewShift(Shift request) {
        try {
            rShift.save(request);

            return new AppResponse("Thêm ca làm việc thành công", true);

        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }

    @Override
    public AppResponse updateShift(Shift request) {
        try {
            Optional<Shift> existingShiftOptional = rShift.findById(request.getId());
            if (existingShiftOptional.isEmpty()) {
                return new AppResponse("Ca làm việc không tồn tại", false);
            }

            rShift.save(request);

            return new AppResponse("Chỉnh sửa thông tin ca làm việc thành công", true);

        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }

    @Override
    public AppResponse deleteShift(Integer id) {
        try {
            Optional<Shift> existingShiftOptional = rShift.findById(id);
            if (existingShiftOptional.isEmpty()) {
                return new AppResponse("Ca làm việc không tồn tại", false);
            }

            rShift.delete(existingShiftOptional.get());

            return new AppResponse("Xóa ca làm việc thành công", true);

        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }

    @Override
    public AppResponse registerShift(List<UserShift> request) {
        try {
            List<UserShift> listSuccess = new ArrayList<>();

            for (UserShift u: request) {
                Optional<Shift> optShift = rShift.findById(u.getShiftId());
                if (optShift.isEmpty()) {
                    return new AppResponse("Ca làm việc không tồn tại", false);
                }

                Shift shift = optShift.get();
                if (shift.getQuantity() <= shift.getQuantityRegistered() || shift.getIsSelected()) {
                    return new AppResponse("Ca làm việc không đăng ký được nữa", false);
                }

                shift.setQuantityRegistered(shift.getQuantityRegistered() + 1);
                if (shift.getQuantityRegistered().equals(shift.getQuantity())) {
                    shift.setIsSelected(true);
                }

                listSuccess.add(u);
                rShift.save(shift);
                rUserShift.save(u);
            }

            return new AppResponse(listSuccess);

        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
