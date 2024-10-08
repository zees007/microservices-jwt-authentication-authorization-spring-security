package com.devzees.appointment.clients;

import com.devzees.appointment.dtos.HealthProvider;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.time.LocalDate;
import java.util.List;

/**
 * Author: Zeeshan Adil
 * User:mhmdz
 * Date:09-10-2024
 * Time:01:52
 */

public interface HealthProviderClient {
    @GetExchange("/api/v1/healthproviders/available")
    List<HealthProvider> getAllAvailableHealthProviderByDateAndDepartment(@RequestParam LocalDate selectedDate, @RequestParam String department);


}