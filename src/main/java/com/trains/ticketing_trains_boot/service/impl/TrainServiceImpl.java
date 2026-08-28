package com.trains.ticketing_trains_boot.service.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trains.ticketing_trains_boot.dto.TrainRequest;
import com.trains.ticketing_trains_boot.dto.TrainResponse;
import com.trains.ticketing_trains_boot.entity.Train;
import com.trains.ticketing_trains_boot.repository.TrainRepository;
import com.trains.ticketing_trains_boot.service.TrainService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrainServiceImpl implements TrainService {

    private final TrainRepository trainRepository;

    @Override
    @Transactional
    @CacheEvict(value = {"trains", "trains_search"}, allEntries = true)
    public TrainResponse createTrain(TrainRequest request) {
        Train train = Train.builder()
                .trainNumber(request.getTrainNumber())
                .startStation(request.getStartStation())
                .endStation(request.getEndStation())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .seatNumber(request.getSeatNumber())
                .price(request.getPrice())
                .build();

        Train savedTrain = trainRepository.save(train);
        return mapToResponse(savedTrain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrainResponse> getAllTrains() {
        return trainRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "trains", key = "#id")
    public TrainResponse getTrainById(Integer id) {
        Train train = trainRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Train not found with id: " + id));
        return mapToResponse(train);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "trains_search", key = "#startStation + '_' + #endStation")
    public List<TrainResponse> searchTrains(String startStation, String endStation) {
        return trainRepository.findByStartStationAndEndStation(startStation, endStation)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional
    @CacheEvict(value = {"trains", "trains_search"}, allEntries = true)
    public void deleteTrain(Integer id) {
        if (!trainRepository.existsById(id)) {
            throw new RuntimeException("Train not found with id: " + id);
        }
        trainRepository.deleteById(id);
    }

    private TrainResponse mapToResponse(Train train) {
        return TrainResponse.builder()
                .trainId(train.getTrainId())
                .trainNumber(train.getTrainNumber())
                .startStation(train.getStartStation())
                .endStation(train.getEndStation())
                .startTime(train.getStartTime())
                .endTime(train.getEndTime())
                .seatNumber(train.getSeatNumber())
                .price(train.getPrice())
                .build();
    }
}
