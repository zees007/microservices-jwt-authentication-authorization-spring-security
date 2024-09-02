package com.devzees.healthprovider.services;

import com.devzees.healthprovider.models.HealthProvider;

import java.time.LocalDate;
import java.util.List;

/**
 * Author: Zeeshan Adil
 * User:mhmdz
 * Date:02-09-2024
 * Time:22:17
 */

public interface HealthProviderService {

    String createHealthProvider(HealthProvider healthProvider);
    List<HealthProvider> getAllAvailableHealthProviderByDateAndDepartment(LocalDate selectedDate, String department);
}