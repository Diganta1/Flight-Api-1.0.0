package com.travel.exercise.controller;

import com.travel.exercise.dto.FlightsList;
import com.travel.exercise.exception.FlightNotFoundException;
import com.travel.exercise.service.FlightService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/flight")
@SecurityRequirement(name = "flightapi")
public class FlightController {

    @Autowired
    private FlightService flightService;

    /**
     * Controller to fetch all the locations
     * @return list of locations
     */
    @GetMapping("/routes")
        public ResponseEntity<FlightsList> getLocationInformation(@Valid @RequestParam (required = true) String origin, @Valid @RequestParam (required = true) String destination, @RequestParam (defaultValue = "price,desc") List<String> sortBy ) throws FlightNotFoundException {
        log.info("FLIGHT_API: Entered flight endpoint");
        return ResponseEntity.ok(new FlightsList(flightService.getFlightInformation(origin,destination,sortBy)));
        }

    @GetMapping("/allroutes")
    public ResponseEntity<FlightsList> getAllLocationInformation() throws FlightNotFoundException {
        log.info("FLIGHT_API: Entered flight endpoint");
        return ResponseEntity.ok(new FlightsList(flightService.getAllFlightInformation()));
    }
}
