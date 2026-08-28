package com.trains.ticketing_trains_boot.service;

import java.util.List;

import com.trains.ticketing_trains_boot.dto.BookingRequest;
import com.trains.ticketing_trains_boot.dto.BookingResponse;

public interface BookingService {

    BookingResponse createBooking(BookingRequest request);

    List<BookingResponse> getAllBookings();

    List<BookingResponse> getBookingsByMemberId(Integer memberId);

    BookingResponse getBookingById(Integer id);

    BookingResponse payBooking(Integer bookingId);

    BookingResponse changeBookingTrain(Integer bookingId, Integer newTrainId);
    
    void cancelBooking(Integer id);
}

