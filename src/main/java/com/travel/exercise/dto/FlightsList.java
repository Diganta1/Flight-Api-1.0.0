package com.travel.exercise.dto;

import com.travel.exercise.entity.Flight;
import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class FlightsList {

    private List<Flight> flightResponseList;

}
