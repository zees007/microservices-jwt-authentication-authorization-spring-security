package com.devzees.auth.services;

import com.devzees.auth.dtos.JwtResponseDTO;
import com.devzees.auth.models.User;

/**
 * Author: Zeeshan Adil
 * User:mhmdz
 * Date:26-10-2024
 * Time:15:17
 */

public interface AuthService {
    String createUser(User user);
    JwtResponseDTO generateToken(String username);
    void validateToken(String jwtToken);
}