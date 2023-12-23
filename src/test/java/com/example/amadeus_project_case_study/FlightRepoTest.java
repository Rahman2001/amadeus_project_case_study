package com.example.amadeus_project_case_study;

import com.example.amadeus_project_case_study.domains.Airport;
import com.example.amadeus_project_case_study.domains.Flight;
import com.example.amadeus_project_case_study.repository.FlightRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class FlightRepoTest {
    @Autowired
    private FlightRepo flightRepo;
    private static final DateTimeFormatter dateFormat= DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @Test
    public void flightInsertTest() {
        LocalDateTime depTime = LocalDateTime.parse(LocalDateTime.now().format(dateFormat), dateFormat);
        LocalDateTime arrTime = depTime.plusMonths(1);

        Airport depAirport = Airport.builder().ID(1L).city("Ankara").build();
        Airport arrAirport = Airport.builder().ID(2L).city("Istanbul").build();
        Flight flight = Flight.builder()
                .departureAirport(depAirport)
                .arrivalAirport(arrAirport)
                .departureTime(depTime)
                .returnTime(arrTime)
                .price(255)
                .build();
        this.flightRepo.save(flight);
        List<Flight> flightList = this.flightRepo.getFlightsBy(flight.getDepartureAirport().getID(),
                flight.getArrivalAirport().getID(), flight.getDepartureTime(), flight.getReturnTime());
        assertFalse(flightList.isEmpty());

    }
}
