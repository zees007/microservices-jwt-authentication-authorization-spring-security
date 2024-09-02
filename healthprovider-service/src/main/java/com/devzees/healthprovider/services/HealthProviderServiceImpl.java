package com.devzees.healthprovider.services;

/**
 * Author: Zeeshan Adil
 * User:mhmdz
 * Date:02-09-2024
 * Time:22:18
 */

import com.devzees.healthprovider.models.HealthProvider;
import com.devzees.healthprovider.repositories.HealthProviderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class HealthProviderServiceImpl implements HealthProviderService{

    private HealthProviderRepository healthProviderRepository;
    @Override
    public String createHealthProvider(HealthProvider healthProvider) {
        return healthProviderRepository.save(healthProvider).getId();

    }

    @Override
    public List<HealthProvider> getAllAvailableHealthProviderByDateAndDepartment(LocalDate selectedDate, String department) {
        if(selectedDate == null){
            throw new RuntimeException("Kindly provide the date of appointment.");
        }
        if(department.isBlank() || department.isEmpty()){
            throw new RuntimeException("Kindly provide the department of health provider.");
        }

        // Convert LocalDate to DayOfWeek
        DayOfWeek selectedDayOfWeek = selectedDate.getDayOfWeek();
        // Convert DayOfWeek to its numerical value (1 = Monday, 2 = Tuesday, ..., 7 = Sunday)
        Long dayOfWeekValue = (long) selectedDayOfWeek.getValue();

        // Call repository method with converted day of the week
        return healthProviderRepository.findProvidersByDateAndDepartment(department, dayOfWeekValue);
    }
}