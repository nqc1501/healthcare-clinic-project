package com.example.facilityscheduling.payload.req;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScheduleRequest {

    private String doctorId;
    private Integer roomId;
}
