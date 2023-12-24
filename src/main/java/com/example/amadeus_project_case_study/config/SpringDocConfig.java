package com.example.amadeus_project_case_study.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public GroupedOpenApi controllerApi() {
        return GroupedOpenApi.builder()
                .group("com.example")
                .packagesToScan("com.example.amadeus_project_case_study.controller") // Specify the package to scan
                .build();
    }



}
