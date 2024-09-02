package com.devzees.healthprovider.repositories;

import com.devzees.healthprovider.models.HealthProvider;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: Zeeshan Adil
 * User:mhmdz
 * Date:02-09-2024
 * Time:22:18
 */
@Repository
public interface HealthProviderRepository extends MongoRepository<HealthProvider, String> {

    @Query("{ 'hpDepartment': ?0, $or: [ { 'isDailyAvailable': true }, { 'daysAvailable': ?1, 'isDailyAvailable': false } ] }")
    List<HealthProvider> findProvidersByDateAndDepartment(String department, Long dayOfWeekValue);

}