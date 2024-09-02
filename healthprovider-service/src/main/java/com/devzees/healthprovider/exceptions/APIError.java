package com.devzees.healthprovider.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Author: Zeeshan Adil
 * User:mhmdz
 * Date:02-09-2024
 * Time:22:37
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIError {

    private HttpStatus status;
    private List<String> errors;
    private LocalDateTime timeStamp;
    private String pathUri;
}