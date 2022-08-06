package com.travel.exercise.service;

import com.travel.exercise.entity.Flight;
import com.travel.exercise.exception.FlightNotFoundException;
import com.travel.exercise.repository.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    private static final Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);
    private static final String COMMA_VALUE = ",";
    private FlightRepository flightRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    /**
     * This is the method to get all flight info list
     *
     * @return
     * @param origin
     * @param destination
     * @param sortBy
     */
    @Override
    public List<Flight> getFlightInformation(String origin, String destination, List<String> sortBy) throws FlightNotFoundException {
        //To check passed language in header reflects
        String language = LocaleContextHolder.getLocale().getLanguage();
        Sort sortOrder = Sort.by(getSortedAttributes(sortBy));
        List<Flight> flightResponse = flightRepository.findAll(sortOrder);
        List<Flight> filterResponse = flightResponse.stream().filter(flight -> flight.getOrigin().equalsIgnoreCase(origin) && flight.getDestination().equalsIgnoreCase(destination)).collect(Collectors.toList());
            if (filterResponse.isEmpty())
            {
                logger.error("FLIGHT_API: invalid route");
                throw new FlightNotFoundException();
            }
            logger.info("FLIGHT_API: filterResponse ={}", filterResponse);
            return filterResponse;
    }

    /**
     *This method sorted the field based on input parameter
     *
     * @param sortBy
     * @return
     */
    private List<Order> getSortedAttributes(List<String> sortBy) {
        List<Order> orders = new ArrayList<Order>();
        Map<String, String> map = new LinkedHashMap<String, String>();
        if (sortBy.get(0).contains(COMMA_VALUE)) {
            map = sortBy.stream()
                    .map(s -> s.split(COMMA_VALUE))
                    .collect(Collectors.toMap(a -> a[0], a ->  a[1]));
            map.forEach((k, v) -> orders.add(new Order(getSortDirection(v), k)));
            logger.info("FLIGHT_API: sorted Field for multi sort query param={}", orders);
            return orders;
        }
            orders.add(new Order(getSortDirection(sortBy.get(1)), sortBy.get(0)));
            logger.info("FLIGHT_API: sorted Field for single sort query param={}", orders);
            return orders;
        }

    /**
     * This method map the direction for sorting (Asc or Desc)
     * @param direction
     * @return
     */
    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }


    @Override
    public List<Flight> getAllFlightInformation() {
        List<Flight> flightResponse = flightRepository.findAll();
        return  flightResponse;
    }
}