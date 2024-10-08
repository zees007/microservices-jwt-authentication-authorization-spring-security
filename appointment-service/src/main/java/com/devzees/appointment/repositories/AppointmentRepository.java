package com.devzees.appointment.repositories;

import com.devzees.appointment.models.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Zeeshan Adil
 * User:mhmdz
 * Date:09-10-2024
 * Time:01:45
 */

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
}