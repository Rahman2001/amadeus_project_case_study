package com.example.amadeus_project_case_study;

import com.example.amadeus_project_case_study.domains.Airport;
import com.example.amadeus_project_case_study.domains.Flight;
import com.example.amadeus_project_case_study.repository.FlightRepo;
import com.example.amadeus_project_case_study.services.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FlightServiceTest {
    @Mock
    private FlightRepo flightRepo;

    @InjectMocks
    private FlightService flightService;
    private List<Flight> flightList;

    @BeforeEach
    public void createList() {
        Airport airport = Airport.builder().ID(1L).city("Istanbul").build();
        Flight flight = Flight.builder()
                .departureAirport(airport)
                .arrivalAirport(airport)
                .departureTime(LocalDateTime.now())
                .price(1000)
        .build();
        this.flightList = List.of(flight);
    }
    @Test
    public void getTwoWayFlights() {
        Flight flight = this.flightList.get(0);
        when(this.flightRepo.getFlightsBy(flight.getDepartureAirport().getID(), flight.getArrivalAirport().getID(),
                flight.getDepartureTime(), flight.getReturnTime())).thenReturn(this.flightList);
        List<Flight> flights = this.flightService.getTwoWayFlights(this.flightList.get(0));

        assertFalse(flights.isEmpty());
    }

    @Test
    public void getOneWayFlights() {
        Flight flight = this.flightList.get(0);
        when(this.flightRepo.getFlightsBy(flight.getDepartureAirport().getID(), flight.getArrivalAirport().getID(),
                flight.getDepartureTime())).thenReturn(this.flightList);
        List<Flight> flights = this.flightService.getOneWayFlights(this.flightList.get(0));

        assertFalse(flights.isEmpty());
    }

}
