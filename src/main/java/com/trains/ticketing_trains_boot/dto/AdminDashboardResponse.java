package com.trains.ticketing_trains_boot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminDashboardResponse {
    private long totalMembers;
    private long totalAdmins;
    private long totalTrains;
    private long totalBookings;
    private long totalRevenue;
}
