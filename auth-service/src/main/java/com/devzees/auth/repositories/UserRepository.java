package com.devzees.auth.repositories;

import com.devzees.auth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Zeeshan Adil
 * User:mhmdz
 * Date:26-10-2024
 * Time:15:12
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}