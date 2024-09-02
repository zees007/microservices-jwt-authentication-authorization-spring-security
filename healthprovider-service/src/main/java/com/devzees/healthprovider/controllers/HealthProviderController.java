package com.devzees.healthprovider.controllers;

/**
 * Author: Zeeshan Adil
 * User:mhmdz
 * Date:02-09-2024
 * Time:22:19
 */

import com.devzees.healthprovider.models.HealthProvider;
import com.devzees.healthprovider.services.HealthProviderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
public class HealthProviderController {

    private HealthProviderService healthProviderService;

    @PostMapping("/api/v1/healthproviders/create")
    public ResponseEntity<String> createHealthProvider(@Valid @RequestBody HealthProvider healthProvider){
        try{
            return ResponseEntity.ok(healthProviderService.createHealthProvider(healthProvider));
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @GetMapping("/api/v1/healthproviders/available")
    public ResponseEntity<List<HealthProvider>> getAllAvailableHealthProviderByDateAndDepartment(@RequestParam LocalDate selectedDate, @RequestParam String department){
        try{
            return ResponseEntity.ok(healthProviderService.getAllAvailableHealthProviderByDateAndDepartment(selectedDate, department));
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

}