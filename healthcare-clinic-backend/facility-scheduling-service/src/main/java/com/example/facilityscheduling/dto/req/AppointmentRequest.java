package com.example.facilityscheduling.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequest {

    private Date date;
    private String dayOfWeek;
    private String hour;
}
