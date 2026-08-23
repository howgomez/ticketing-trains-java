package com.trains.ticketing_trains_boot.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.trains.ticketing_trains_boot.entity.Train;

@Repository
public interface TrainRepository extends JpaRepository<Train, Integer> {

    /**
     * Spring Data JPA derives SQL query automatically:
     * SELECT * FROM trains WHERE start_station = ? AND end_station = ?
     */
    List<Train> findByStartStationAndEndStation(String startStation, String endStation);
}
