package com.trains.ticketing_trains_boot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trains.ticketing_trains_boot.dto.TrainRequest;
import com.trains.ticketing_trains_boot.dto.TrainResponse;
import com.trains.ticketing_trains_boot.service.TrainService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/trains")
@RequiredArgsConstructor
public class TrainController {

    private final TrainService trainService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TrainResponse> createTrain(@RequestBody TrainRequest request) {
        TrainResponse created = trainService.createTrain(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<TrainResponse>> getAllTrains() {
        return ResponseEntity.ok(trainService.getAllTrains());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainResponse> getTrainById(@PathVariable Integer id) {
        return ResponseEntity.ok(trainService.getTrainById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<TrainResponse>> searchTrains(
            @RequestParam String startStation,
            @RequestParam String endStation) {
        return ResponseEntity.ok(trainService.searchTrains(startStation, endStation));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrain(@PathVariable Integer id) {
        trainService.deleteTrain(id);
        return ResponseEntity.noContent().build();
    }
}
