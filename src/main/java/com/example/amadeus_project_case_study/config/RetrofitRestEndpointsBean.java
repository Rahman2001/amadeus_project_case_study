package com.example.amadeus_project_case_study.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@EnableConfigurationProperties(RetrofitRestEndpoints.class)
public class RetrofitRestEndpointsBean {
    private List<EndpointProperties> endpoints;

    @Autowired
    public RetrofitRestEndpointsBean(RetrofitRestEndpoints retrofitRestEndpoints) {
        this.endpoints = retrofitRestEndpoints.getEndpoints();
    }

    @Bean("restEndpoints")
    public List<EndpointProperties> endpoints() {
        return this.endpoints;
    }
}
