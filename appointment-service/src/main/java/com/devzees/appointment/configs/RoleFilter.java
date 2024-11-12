package com.devzees.appointment.configs;

/**
 * Author: Zeeshan Adil
 * User:mhmdz
 * Date:12-11-2024
 * Time:22:23
 */

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // Read roles from the request header set by the API Gateway
        String rolesHeader = request.getHeader("roles");
        String username = request.getHeader("loggedInUser");
        if (rolesHeader != null) {
            String[] roles = rolesHeader.split(",");

            // Convert roles to authorities and ensure each has "ROLE_" prefix
            List<GrantedAuthority> authorities = Arrays.stream(roles)
                    .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role) // Add "ROLE_" prefix if missing
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            // Set authentication in SecurityContext
            Authentication auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(request, response);
    }

}