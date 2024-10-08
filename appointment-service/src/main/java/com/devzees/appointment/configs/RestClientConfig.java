package com.devzees.appointment.configs;

import com.devzees.appointment.clients.HealthProviderClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * Author: Zeeshan Adil
 * User:mhmdz
 * Date:09-10-2024
 * Time:01:56
 */

@Configuration
public class RestClientConfig {

    @Bean
    public HealthProviderClient healthProviderClient() {
        RestClient restClient = RestClient.builder()
                .baseUrl("inventoryServiceUrl")
                .build();
        var restClientAdapter = RestClientAdapter.create(restClient);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpServiceProxyFactory.createClient(HealthProviderClient.class);
    }


}