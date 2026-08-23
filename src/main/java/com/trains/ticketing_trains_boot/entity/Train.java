package com.trains.ticketing_trains_boot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trains")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_id")
    private Integer trainId;

    @Column(name = "train_number", nullable = false)
    private String trainNumber;

    @Column(name = "start_station", nullable = false)
    private String startStation;

    @Column(name = "end_station", nullable = false)
    private String endStation;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "seat_number", nullable = false)
    private Integer seatNumber;

    @Column(name = "price", nullable = false)
    private Integer price;
}
