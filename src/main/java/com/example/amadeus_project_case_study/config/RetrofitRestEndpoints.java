package com.example.amadeus_project_case_study.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "retrofit")
public class RetrofitRestEndpoints {
    private List<EndpointProperties> endpoints;
}
