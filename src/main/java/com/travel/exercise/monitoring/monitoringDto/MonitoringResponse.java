package com.travel.exercise.monitoring.monitoringDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * This is the DTO class for the location responses for the client
 */
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class MonitoringResponse implements Serializable {
    private Integer totalRequest = 0;
    private Integer totalOkRequest = 0;
    private Integer total4xxRequest = 0;
    private Integer total5xxRequest = 0;
    private Float avgResponseTime = 0.0f;
    private Float totalResponseTime = 0.0f;
}
