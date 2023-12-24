package com.example.amadeus_project_case_study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableScheduling
public class AmadeusProjectCaseStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmadeusProjectCaseStudyApplication.class, args);
    }

}
