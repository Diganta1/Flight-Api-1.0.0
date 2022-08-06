package com.travel.exercise.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Getter
@AllArgsConstructor
@ToString
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @Column(name = "flight_number")
    private String flightNumber;
    @Column(name = "origin")
    private String origin;
    @Column(name = "destination")
    private String destination;
    @Column(name = "departure_time")
    private String departureTime;
    @Column(name = "arrival_time")
    private String arrivalTime;
    @Column(name = "price")
    private Integer price;
}