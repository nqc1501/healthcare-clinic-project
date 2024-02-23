package com.example.serivcemanagement.dto.req;

import com.example.serivcemanagement.models.Service;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SpecialistRequest {

    private String name;
    private List<Service> listService;
}
