package com.devzees.appointment.services;

import java.time.LocalDate;

/**
 * Author: Zeeshan Adil
 * User:mhmdz
 * Date:09-10-2024
 * Time:01:45
 */

public interface AppointmentService {

    String bookAppointment(LocalDate date, String hpDepartment);
}