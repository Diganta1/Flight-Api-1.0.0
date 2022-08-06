package com.travel.exercise.service;

import com.travel.exercise.entity.Flight;
import com.travel.exercise.exception.FlightNotFoundException;

import java.util.List;

public interface FlightService {
    public List<Flight> getFlightInformation(String origin, String destination, List<String> sortBy) throws FlightNotFoundException;

   public List<Flight> getAllFlightInformation();
}
