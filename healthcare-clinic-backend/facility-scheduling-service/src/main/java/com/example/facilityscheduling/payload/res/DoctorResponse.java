package com.example.facilityscheduling.payload.res;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {

    private String id;
    private Integer credentialId;
    private String name;
    private String image;
    private Date birthday;
    private String gender;
    private String tel;
    private String email;
    private String address;
    private String about;
    private Integer specialtyId;
    private String degree;
}
