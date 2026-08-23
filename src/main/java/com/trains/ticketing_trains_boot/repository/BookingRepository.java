package com.trains.ticketing_trains_boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trains.ticketing_trains_boot.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByMember_MemberId(Integer memberId);
    List<Booking> findByTrain_TrainId(Integer trainId);
}
