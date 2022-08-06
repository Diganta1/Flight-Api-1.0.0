package com.travel.exercise.monitoring.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class ActuatorResponseClientConfig {
    @Bean(name = "ActuatorResponseRestTemplate")
    public RestTemplate restTemplate(RestTemplateBuilder builder,
                                     @Value("${actuator.request.client.timeout.connectSeconds}") int connectionTimeoutSeconds,
                                     @Value("${actuator.request.client.timeout.readSeconds}") int readTimeoutSeconds) {
        return builder.setConnectTimeout(Duration.ofMillis(connectionTimeoutSeconds * 1000))
                .setReadTimeout(Duration.ofMillis(readTimeoutSeconds * 1000))
                .build();
    }
}