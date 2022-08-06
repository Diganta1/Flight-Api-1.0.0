package com.travel.exercise.monitoring.services;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("actuator.request.client")
@Data
public class ActuatorRequestClientProperties {
        private String hostname;
        private String port;
        private String baseUrl;
        private String endpoint;
    }
