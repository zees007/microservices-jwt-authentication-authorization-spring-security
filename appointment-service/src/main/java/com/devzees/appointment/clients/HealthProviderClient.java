package com.devzees.appointment.clients;

import com.devzees.appointment.dtos.HealthProvider;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Zeeshan Adil
 * User:mhmdz
 * Date:09-10-2024
 * Time:01:52
 */

public interface HealthProviderClient {

    Logger log = LoggerFactory.getLogger(HealthProviderClient.class);
    @GetExchange("/api/v1/healthproviders/available")
    @CircuitBreaker(name = "healthProvider", fallbackMethod = "getGeneralProviders")
//    @Retry(name = "healthProvider")
    List<HealthProvider> getAvailableHealthProviders(@RequestParam LocalDate selectedDate, @RequestParam String department);

    default List<HealthProvider> getGeneralProviders(Throwable throwable) {

        // Create the specific general HealthProvider object to return as fallback
        HealthProvider fallbackProvider = new HealthProvider();
        fallbackProvider.setHpName("Dr. David Walker");
        fallbackProvider.setHpDepartment("Pediatric");
        fallbackProvider.setIsDailyAvailable(false);
        fallbackProvider.setDaysAvailable(new ArrayList<>());
        fallbackProvider.setDutyStartTime(LocalTime.of(9, 0));
        fallbackProvider.setDutyEndTime(LocalTime.of(15, 0));

        // Return a list containing the specified fallback provider
        return Collections.singletonList(fallbackProvider);
    }

}