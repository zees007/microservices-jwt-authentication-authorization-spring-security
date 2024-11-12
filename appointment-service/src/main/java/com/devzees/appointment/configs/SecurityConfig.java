package com.devzees.appointment.configs;

/**
 * Author: Zeeshan Adil
 * User:mhmdz
 * Date:12-11-2024
 * Time:22:24
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

   @Autowired
   RoleFilter roleFilter;

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      return http
              .csrf().disable()
              .authorizeHttpRequests((authorize) -> authorize
                      .anyRequest().authenticated()
              )
              .addFilterBefore(roleFilter, UsernamePasswordAuthenticationFilter.class)
              .build();
   }

}