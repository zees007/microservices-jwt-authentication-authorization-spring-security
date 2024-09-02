package com.devzees.healthprovider.models;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.List;

/**
 * Author: Zeeshan Adil
 * User:mhmdz
 * Date:02-09-2024
 * Time:22:16
 */

@Data
@Document(collection = "health_providers")
public class HealthProvider {

    @Id
    private String id;

    @NotBlank(message = "Health provider name is required")
    private String hpName;

    @NotBlank(message = "Department is required")
    private String hpDepartment;

    @NotNull(message = "Availability status is required")
    private Boolean isDailyAvailable;

    @Size(max = 7, message = "Days available should not exceed 7 days")
    private List<Long> daysAvailable;

    @NotNull(message = "Duty start time is required")
    private LocalTime dutyStartTime;

    @NotNull(message = "Duty end time is required")
    private LocalTime dutyEndTime;


    @AssertTrue(message = "DaysAvailable must be empty when DailyAvailable is true, or not empty when false")
    public boolean isDaysAvailableValid() {
        if (isDailyAvailable == null) {
            return true;
        }
        return (isDailyAvailable && (daysAvailable == null || daysAvailable.isEmpty()))
                || (!isDailyAvailable && (daysAvailable != null && !daysAvailable.isEmpty()));
    }

}