package com.trains.ticketing_trains_boot.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trains.ticketing_trains_boot.dto.AdminDashboardResponse;
import com.trains.ticketing_trains_boot.entity.Booking;
import com.trains.ticketing_trains_boot.entity.Role;
import com.trains.ticketing_trains_boot.repository.BookingRepository;
import com.trains.ticketing_trains_boot.repository.MemberRepository;
import com.trains.ticketing_trains_boot.repository.TrainRepository;
import com.trains.ticketing_trains_boot.service.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final MemberRepository memberRepository;
    private final TrainRepository trainRepository;
    private final BookingRepository bookingRepository;

    @Override
    @Transactional(readOnly = true)
    public AdminDashboardResponse getDashboardStats() {
        long totalMembers = memberRepository.countByRole(Role.ROLE_MEMBER);
        long totalAdmins = memberRepository.countByRole(Role.ROLE_ADMIN);
        long totalTrains = trainRepository.count();
        long totalBookings = bookingRepository.count();

        long totalRevenue = bookingRepository.findAll()
                .stream()
                .mapToLong(Booking::getPrice)
                .sum();

        return AdminDashboardResponse.builder()
                .totalMembers(totalMembers)
                .totalAdmins(totalAdmins)
                .totalTrains(totalTrains)
                .totalBookings(totalBookings)
                .totalRevenue(totalRevenue)
                .build();
    }
}
