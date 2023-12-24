package com.example.amadeus_project_case_study.services;

import com.example.amadeus_project_case_study.config.RetrofitClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;


@Component
@Slf4j
public class ScheduledService {

    private RetrofitClientConfig retrofitClientConfig;

    @Autowired
    public ScheduledService(RetrofitClientConfig retrofitClientConfig) {
        this.retrofitClientConfig = retrofitClientConfig;
    }

    @Scheduled(cron = "0/7 * * * * *") //each 7 seconds  //to schedule for each day, write * * * 0/1 * *
    public void scheduleRequest() {
        log.info(LocalTime.now() + " scheduled job completed");
    }
}
