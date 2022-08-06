package com.travel.exercise.monitoring.services;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("actuator.response.client")
@Data
public class ActuatorResponseClientProperties {
        private String hostname;
        private String port;
        private String baseUrl;
        private String endpoint;
    }
