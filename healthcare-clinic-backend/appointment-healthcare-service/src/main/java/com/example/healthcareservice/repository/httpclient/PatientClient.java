package com.example.healthcareservice.repository.httpclient;

import com.example.healthcareservice.dto.res.PatientResponse;
import com.example.responsehandling.payload.response.AppResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "${app.services.patient}")
public interface PatientClient {

    @GetMapping(value = "/by-health-code/{healthCode}", consumes = "application/json")
    PatientResponse getByHealthCode(@PathVariable String healthCode);
}
