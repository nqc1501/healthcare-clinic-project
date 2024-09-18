package com.example.healthcareservice.service.impl;

import com.example.healthcareservice.model.item.Supply;
import com.example.healthcareservice.dto.req.SupplyRequest;
import com.example.healthcareservice.repository.SupplyRepository;
import com.example.healthcareservice.service.SupplyService;
import com.example.responsehandling.payload.response.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyServiceImpl implements SupplyService {

    @Autowired
    SupplyRepository rSupply;

    @Override
    public List<Supply> getAllSupply() {
        return rSupply.findAll();
    }

    @Override
    public AppResponse addSupply(Supply supply) {
        try {
            rSupply.save(supply);

            return new AppResponse("Thêm mới thiết bị thành công", true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }

    @Override
    public AppResponse addSupplyToRoom(Integer roomId, SupplyRequest request) {
        try {
            Supply existingSupply = rSupply.findById(request.getId()).orElse(null);
            if (existingSupply == null) {
                return new AppResponse("Thiết bị không tồn tại", false);
            }

            existingSupply.setRoomId(roomId);
            rSupply.save(existingSupply);

            return new AppResponse("Thêm thiết bị vào phòng thành công", true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }

    @Override
    public AppResponse updateSupply(Supply supply) {
        try {
            Supply existingSupply = rSupply.findById(supply.getId()).orElse(null);
            if (existingSupply == null) {
                return new AppResponse("Thiết bị không tồn tại", false);
            }

            rSupply.save(supply);

            return new AppResponse("Cập nhật thiết bị thành công", true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }

    @Override
    public AppResponse deleteSupply(Integer id) {
        try {
            Supply existingSupply = rSupply.findById(id).orElse(null);
            if (existingSupply == null) {
                return new AppResponse("Thiết bị không tồn tại", false);
            }

            rSupply.delete(existingSupply);

            return new AppResponse("Xóa thiết bị thành công", true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
