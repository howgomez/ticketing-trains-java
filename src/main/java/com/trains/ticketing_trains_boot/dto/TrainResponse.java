package com.trains.ticketing_trains_boot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainResponse {
    private Integer trainId;
    private String trainNumber;
    private String startStation;
    private String endStation;
    private String startTime;
    private String endTime;
    private Integer seatNumber;
    private Integer price;
}
