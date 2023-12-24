package com.example.amadeus_project_case_study.repository;

import com.example.amadeus_project_case_study.domains.Flight;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface ExternalFlightAPIService {
    @GET("/getFlights")
    Call<List<Flight>> getFlights();
}
