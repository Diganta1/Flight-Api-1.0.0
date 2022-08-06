package com.travel.exercise.monitoring.services;

import com.travel.exercise.monitoring.monitoringDto.MeasurementsList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class ActuatorRequestClient {
    private static final Logger logger = LoggerFactory.getLogger(ActuatorRequestClient.class);

    private final RestTemplate restTemplate;
    private final ActuatorRequestClientProperties actuatorRequestClientProperties;

    @Autowired
    public ActuatorRequestClient(@Qualifier("ActuatorRequestRestTemplate") RestTemplate restTemplate, ActuatorRequestClientProperties actuatorRequestClientProperties) {
        this.restTemplate = restTemplate;
        this.actuatorRequestClientProperties = actuatorRequestClientProperties;
    }

    public ResponseEntity<MeasurementsList> getMesurement() {

        String uriString = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(actuatorRequestClientProperties.getHostname())
                .port(actuatorRequestClientProperties.getPort())
                .path(actuatorRequestClientProperties.getBaseUrl())
                .path(actuatorRequestClientProperties.getEndpoint())
                .toUriString();

        ResponseEntity<MeasurementsList> measurementResponse
                = restTemplate.getForEntity(uriString , MeasurementsList.class);

        logger.debug("FLIGHT_API:getMesurement, measurementResponse={}", measurementResponse);
        return measurementResponse;
    }
}
