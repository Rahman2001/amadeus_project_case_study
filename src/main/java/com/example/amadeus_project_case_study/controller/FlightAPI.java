package com.example.amadeus_project_case_study.controller;

import com.example.amadeus_project_case_study.domains.Airport;
import com.example.amadeus_project_case_study.domains.Flight;
import com.example.amadeus_project_case_study.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flight")
public class FlightAPI {
    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm";
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    private final FlightService flightService;

    @Autowired
    public FlightAPI(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/getFlights")
    public ResponseEntity<List<Flight>> getFlightList(@RequestParam("depAir") String depAirId,
                                                     @RequestParam("arrAir") String arrAirId,
                                                     @RequestParam("depTime") String depTime,
                                                     @RequestParam(value = "arrTime", required = false) String arrTime) {
        LocalDateTime depDate = LocalDateTime.parse(depTime, dateFormatter);
        LocalDateTime arrDate = arrTime == null || arrTime.isEmpty() ? null : LocalDateTime.parse(arrTime, dateFormatter);

        Airport depAirport = Airport.builder().ID(Long.parseLong(depAirId)).build();
        Airport arrAirport = Airport.builder().ID(Long.parseLong(arrAirId)).build();
        Flight reqFlight = Flight.builder()
                        .departureAirport(depAirport)
                                .arrivalAirport(arrAirport)
                                        .returnTime(arrDate)
                                                .departureTime(depDate).build();

        Optional<List<Flight>> optionalFlights = Optional.of(arrTime == null || arrTime.isEmpty() ?
                this.flightService.getOneWayFlights(reqFlight) : this.flightService.getTwoWayFlights(reqFlight));

        return optionalFlights.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());

    }
}
