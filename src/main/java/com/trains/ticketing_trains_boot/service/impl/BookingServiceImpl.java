package com.trains.ticketing_trains_boot.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trains.ticketing_trains_boot.dto.BookingRequest;
import com.trains.ticketing_trains_boot.dto.BookingResponse;
import com.trains.ticketing_trains_boot.entity.Booking;
import com.trains.ticketing_trains_boot.entity.Member;
import com.trains.ticketing_trains_boot.entity.Train;
import com.trains.ticketing_trains_boot.repository.BookingRepository;
import com.trains.ticketing_trains_boot.repository.MemberRepository;
import com.trains.ticketing_trains_boot.repository.TrainRepository;
import com.trains.ticketing_trains_boot.service.BookingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final MemberRepository memberRepository;
    private final TrainRepository trainRepository;

    @Override
    @Transactional
    public BookingResponse createBooking(BookingRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + request.getMemberId()));

        Train train = trainRepository.findById(request.getTrainId())
                .orElseThrow(() -> new RuntimeException("Train not found with id: " + request.getTrainId()));

        if (train.getSeatNumber() <= 0) {
            throw new RuntimeException("No seats available for train: " + train.getTrainNumber());
        }

        // Regla de negocio: Decrementar 1 asiento disponible del tren
        train.setSeatNumber(train.getSeatNumber() - 1);
        trainRepository.save(train);

        Booking booking = Booking.builder()
                .member(member)
                .train(train)
                .price(train.getPrice())
                .isPaid(false)
                .build();

        Booking saved = bookingRepository.save(booking);
        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingResponse> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingResponse> getBookingsByMemberId(Integer memberId) {
        return bookingRepository.findByMember_MemberId(memberId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public BookingResponse getBookingById(Integer id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
        return mapToResponse(booking);
    }

    @Override
    @Transactional
    public void cancelBooking(Integer id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));

        // Regla de negocio: Restaurar el asiento al cancelar
        Train train = booking.getTrain();
        train.setSeatNumber(train.getSeatNumber() + 1);
        trainRepository.save(train);

        bookingRepository.delete(booking);
    }

    private BookingResponse mapToResponse(Booking booking) {
        return BookingResponse.builder()
                .bookingId(booking.getBookingId())
                .memberId(booking.getMember().getMemberId())
                .memberUsername(booking.getMember().getUsername())
                .trainId(booking.getTrain().getTrainId())
                .trainNumber(booking.getTrain().getTrainNumber())
                .startStation(booking.getTrain().getStartStation())
                .endStation(booking.getTrain().getEndStation())
                .price(booking.getPrice())
                .isPaid(booking.getIsPaid())
                .bookingTime(booking.getBookingTime())
                .build();
    }
}
