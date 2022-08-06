package com.travel.exercise.monitoring.monitoringDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * DTO class for the location list for client services
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TracesList implements Serializable {

    private List<Trace> traces;
}
