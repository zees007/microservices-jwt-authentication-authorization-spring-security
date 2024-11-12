package com.devzees.apigateway.configs;

/**
 * Author: Zeeshan Adil
 * User:mhmdz
 * Date:26-10-2024
 * Time:17:50
 */

import com.devzees.apigateway.helpers.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Set;

@Component
public class JwtFilter extends AbstractGatewayFilterFactory<JwtFilter.Config> {

    @Autowired
    RoutingRequestValidator routingRequestValidator;
    @Autowired
    JwtUtils jwtUtils;

    public JwtFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest modifiedRequest = null;
            if (routingRequestValidator.isAuthenticated.test(exchange.getRequest())) {
                // Check for Authorization header
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authorization Header is missing");
                }

                String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
                if (authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7); // Remove 'Bearer ' prefix
                }

                try {
                    jwtUtils.validateToken(authHeader); // Validate token using JwtUtils
                    String username = jwtUtils.extractUsername(authHeader);  // Extract username
                    Set<String> roles = jwtUtils.extractRoles(authHeader);   // Extract roles

                    // Pass user details to downstream microservices
                    modifiedRequest = exchange.getRequest().mutate()
                            .header("loggedInUser", username)
                            .header("roles", String.join(",", roles))
                            .build();

                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid or expired JWT token");
                }
            }
            assert modifiedRequest != null;
            return chain.filter(exchange.mutate().request(modifiedRequest).build()); // Proceed if valid
        };
    }

    public static class Config{

    }
}