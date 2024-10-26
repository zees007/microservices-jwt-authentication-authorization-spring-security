package com.devzees.auth.controllers;

/**
 * Author: Zeeshan Adil
 * User:mhmdz
 * Date:26-10-2024
 * Time:15:21
 */

import com.devzees.auth.dtos.AuthRequestDTO;
import com.devzees.auth.dtos.JwtResponseDTO;
import com.devzees.auth.models.User;
import com.devzees.auth.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody User user){
        try{
            return ResponseEntity.ok(authService.createUser(user));
        } catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    @PostMapping("/generateToken")
    public ResponseEntity<JwtResponseDTO> generateToken(@RequestBody AuthRequestDTO authRequestDTO){
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
            if(authentication.isAuthenticated()){
                return ResponseEntity.ok(authService.generateToken(authRequestDTO.getUsername()));
            } else {
                throw new RuntimeException("Invalid User Request..!");
            }

        } catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    @GetMapping("/validateToken")
    public String validateToken(@RequestParam String jwtToken){
        try{
            authService.validateToken(jwtToken);
            return "Token is valid.!";
        } catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

}