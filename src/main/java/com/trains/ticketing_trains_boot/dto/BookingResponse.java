package com.trains.ticketing_trains_boot.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {
    private Integer bookingId;
    private Integer memberId;
    private String memberUsername;
    private Integer trainId;
    private String trainNumber;
    private String startStation;
    private String endStation;
    private Integer price;
    private Boolean isPaid;
    private LocalDateTime bookingTime;
}
