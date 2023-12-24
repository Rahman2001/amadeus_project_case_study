package com.example.amadeus_project_case_study;

import com.example.amadeus_project_case_study.config.RetrofitClientConfig;
import com.example.amadeus_project_case_study.domains.Flight;
import com.example.amadeus_project_case_study.repository.ExternalFlightAPIService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = {RetrofitClientConfig.class})
public class RetrofitClientServiceTest {
    @Autowired
    private ExternalFlightAPIService externalFlightAPIService;

    @Test
    @WithMockUser(username = "Rahman", password = "Rejepov")
    public void getExternalFlightTest() throws IOException {

        Response<List<Flight>> response = this.externalFlightAPIService.getFlights(
                "1", "2", "24-12-2023 17:21", "24-01-2024 17:21"
        ).execute();

//        assertNotNull(newList);
        assertTrue(response.isSuccessful());

    }
}
