package com.example.amadeus_project_case_study.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    @Column
    @Id
    private Long ID;

    @OneToOne
    @JoinColumn(name = "departureAirportId", referencedColumnName = "id")
    private Airport departureAirport;

    @OneToOne
    @JoinColumn(name = "arrivalAirportId", referencedColumnName = "id")
    private Airport arrivalAirport;

    @Column
    private LocalDateTime departureTime;

    @Column
    private LocalDateTime returnTime;

    @Column
    private Integer price;
}
