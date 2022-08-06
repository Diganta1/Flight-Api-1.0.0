package com.travel.exercise.monitoring.services;

import com.travel.exercise.monitoring.monitoringDto.TracesList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class ActuatorResponseClient {
    private static final Logger logger = LoggerFactory.getLogger(ActuatorResponseClient.class);

    private final RestTemplate restTemplate;
    private final ActuatorResponseClientProperties actuatorResponseClientProperties;

    @Autowired
    public ActuatorResponseClient(@Qualifier("ActuatorRequestRestTemplate") RestTemplate restTemplate, ActuatorResponseClientProperties actuatorResponseClientProperties) {
        this.restTemplate = restTemplate;
        this.actuatorResponseClientProperties = actuatorResponseClientProperties;
    }

    public ResponseEntity<TracesList> getTrace() {

        String uriString = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(actuatorResponseClientProperties.getHostname())
                .port(actuatorResponseClientProperties.getPort())
                .path(actuatorResponseClientProperties.getBaseUrl())
                .path(actuatorResponseClientProperties.getEndpoint())
                .toUriString();

        ResponseEntity<TracesList> traceResponse
                = restTemplate.getForEntity(uriString , TracesList.class);

        logger.debug("FLIGHT_API:getTrace, traceResponse={}", traceResponse);
        return traceResponse;
    }
}
