package com.example.servicemanagement.dto.req;

import com.example.servicemanagement.model.Service;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SpecialistRequest {

    private String name;
    private List<Service> listService;
}
