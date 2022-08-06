package com.travel.exercise.monitoring.controller;

import com.travel.exercise.monitoring.monitoringDto.MonitoringResponse;
import com.travel.exercise.monitoring.services.MonitoringService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for the client implementation
 */
@Slf4j
@RestController
//@SecurityRequirement(name = "flightapi")
public class MonitoringController {

    @Autowired
    private MonitoringService monitorService;

    /**
     * Controller to fetch all the airports based on the country
     * @return
     */
    @GetMapping("/actuator/metricsInfo")
    public ResponseEntity<MonitoringResponse> getMonitoringResponse() {
        log.info("Entered actuator metrics info endpoint");
        return ResponseEntity.ok(monitorService.getReport());
    }

    /**
     * Explicitly create a 500 error to check monitoring count
     * @return
     */
    @GetMapping("test500")
    public ResponseEntity.BodyBuilder testServerError() {
        log.info("Created a explicit 500 error");
        return ResponseEntity.status(500);
    }

}
