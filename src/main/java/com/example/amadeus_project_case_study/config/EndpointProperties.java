package com.example.amadeus_project_case_study.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EndpointProperties {
    private String serviceName;
    private String baseUrl;
}
