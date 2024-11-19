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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@Slf4j
@RestController
@AllArgsConstructor
public class HealthProviderController {

    private HealthProviderService healthProviderService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/api/v1/healthproviders/create")
    public ResponseEntity<String> createHealthProvider(@RequestBody HealthProvider healthProvider){
        try{
            return ResponseEntity.ok(healthProviderService.createHealthProvider(healthProvider));
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }


    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/api/v1/healthproviders/available")
    public ResponseEntity<List<HealthProvider>> getAllAvailableHealthProviderByDateAndDepartment(@RequestParam LocalDate selectedDate, @RequestParam String department){
        try{
            return ResponseEntity.ok(healthProviderService.getAllAvailableHealthProviderByDateAndDepartment(selectedDate, department));
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/api/v1/healthproviders/test")
    public String UserRoleHasAccess(){
        try{
            return "You have User Role.";
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

}