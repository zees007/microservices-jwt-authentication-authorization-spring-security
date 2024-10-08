package com.devzees.appointment.services;

import com.devzees.appointment.clients.HealthProviderClient;
import com.devzees.appointment.dtos.HealthProvider;
import com.devzees.appointment.models.Appointment;
import com.devzees.appointment.repositories.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Author: Zeeshan Adil
 * User:mhmdz
 * Date:09-10-2024
 * Time:01:46
 */

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService{

    private final HealthProviderClient healthProviderClient;
    private final AppointmentRepository appointmentRepository;

    @Override
    public String bookAppointment(LocalDate date, String hpDepartment) {
        List<HealthProvider> healthProviders = healthProviderClient.getAllAvailableHealthProviderByDateAndDepartment(date, hpDepartment);
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(date);
        appointment.setHpDepartment(hpDepartment);
        appointment.setHpName(healthProviders.get(0).getHpName());
        appointmentRepository.save(appointment);
        return "Your appointment is confirmed.!";
    }
}