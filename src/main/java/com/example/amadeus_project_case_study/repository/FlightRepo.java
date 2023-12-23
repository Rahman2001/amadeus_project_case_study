package com.example.amadeus_project_case_study.repository;

import com.example.amadeus_project_case_study.domains.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepo extends JpaRepository<Flight, Long> {
    @Query(value = "select * from flight where departure_airport_id = ?1 and arrival_airport_id = ?2 and " +
            "departure_time = ?3 and return_time = ?4",nativeQuery = true)
    List<Flight> getFlightsBy(Long depAirId, Long arrAirId,
                              LocalDateTime depTime, LocalDateTime retTime);

    @Query(value = "select * from flight where departure_airport_id = ?1 and arrival_airport_id = ?2 and " +
            "departure_time = ?3",nativeQuery = true)
    List<Flight> getFlightsBy(Long depAirId, Long arrAirId, LocalDateTime depTime);

}
