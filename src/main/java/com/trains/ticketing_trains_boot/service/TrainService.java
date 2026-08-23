package com.trains.ticketing_trains_boot.service;

import java.util.List;
import com.trains.ticketing_trains_boot.dto.TrainRequest;
import com.trains.ticketing_trains_boot.dto.TrainResponse;

public interface TrainService {
    TrainResponse createTrain(TrainRequest request);
    List<TrainResponse> getAllTrains();
    TrainResponse getTrainById(Integer id);
    List<TrainResponse> searchTrains(String startStation, String endStation);
    void deleteTrain(Integer id);
}
