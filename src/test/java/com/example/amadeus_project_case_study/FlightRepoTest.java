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
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        Flight retFlight = Flight.builder()
                .departureAirport(arrAirport)
                .arrivalAirport(depAirport)
                .departureTime(depTime.plusMonths(2))
                .price(255)
                .build();
        this.flightRepo.save(retFlight);
        retFlight = this.flightRepo.getFlightsBy(retFlight.getDepartureAirport().getID(),
                retFlight.getArrivalAirport().getID(), retFlight.getDepartureTime()).get(0);

        Flight flight = Flight.builder()
                .departureAirport(depAirport)
                .arrivalAirport(arrAirport)
                .departureTime(depTime)
                .returnTime(arrTime)
                .returnFlight(retFlight)
                .price(255)
                .build();
        this.flightRepo.save(flight);
        List<Flight> flightList = this.flightRepo.getFlightsBy(flight.getDepartureAirport().getID(),
                flight.getArrivalAirport().getID(), flight.getDepartureTime(), flight.getReturnTime());
        assertFalse(flightList.isEmpty());
        flight = flightList.get(0);
        assertTrue(flight.getReturnFlight() != null && flight.getReturnFlight().getID() > 0);

    }
}
