package com.example.amadeus_project_case_study.services;

import com.example.amadeus_project_case_study.domains.Flight;
import com.example.amadeus_project_case_study.repository.FlightRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    private final FlightRepo flightRepo;

    @Autowired
    public FlightService(FlightRepo flightRepo) {
        this.flightRepo = flightRepo;
    }

    public List<Flight> getTwoWayFlights(Flight flight) {
        return this.flightRepo.getFlightsBy(flight.getDepartureAirport().getID(),
                flight.getArrivalAirport().getID(), flight.getDepartureTime(), flight.getReturnTime());
    }
    public List<Flight> getOneWayFlights(Flight flight) {
        return this.flightRepo.getFlightsBy(flight.getDepartureAirport().getID(),
                flight.getArrivalAirport().getID(), flight.getDepartureTime());
    }

}
