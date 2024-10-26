package com.devzees.auth.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: Zeeshan Adil
 * User:mhmdz
 * Date:26-10-2024
 * Time:15:16
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponseDTO {

    private String accessToken;
}