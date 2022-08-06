package com.travel.exercise.monitoring.services;

import com.travel.exercise.monitoring.monitoringDto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MonitoringServiceImpl implements MonitoringService {

    @Autowired
    public ActuatorRequestClient actuatorRequestClient;

    @Autowired
    public ActuatorResponseClient actuatorResponseClient;

    @Override
    public MonitoringResponse getReport() {
         MonitoringResponse report = new MonitoringResponse();

        return populateResponseCounts(populateResponseTime(report));
    }

    private MonitoringResponse populateResponseTime(MonitoringResponse report){
        Float countTotal = 1f;
        List<Measurements> measurements = actuatorRequestClient.getMesurement().getBody().getMeasurements();
        measurements.stream().filter(m->m.getStatistic().equalsIgnoreCase("TOTAL_TIME"))
                .forEach( m-> {
                report.setTotalResponseTime(m.getValue());
                });
        measurements.stream().filter(m->m.getStatistic().equalsIgnoreCase("COUNT"))
                .forEach( m-> {
                    report.setAvgResponseTime(report.getTotalResponseTime()/m.getValue());
                });
        return report;
    }

    private MonitoringResponse populateResponseCounts(MonitoringResponse report){
        final Integer[] countTotal = {0};
        final Integer[] countOk = {0};
        final Integer[] count400 = {0};
        final Integer[] count500 = {0};

        List<Trace> traces = actuatorResponseClient.getTrace().getBody().getTraces();
        traces.stream().filter(t->(!(t.getRequest().getUri().contains("actuator") || t.getRequest().getUri().contains("monitoring")))).forEach(t->{
            countTotal[0]++;
            if((t.getResponse().getStatus()/100) == 2){
                countOk[0]++;
            } else  if((t.getResponse().getStatus()/100) == 4){
                count400[0]++;
            } else  if(t.getResponse().getStatus()/100 == 5){
                count500[0]++;
            }
        });

        report.setTotalRequest(countTotal[0]);
        report.setTotalOkRequest(countOk[0]);
        report.setTotal4xxRequest(count400[0]);
        report.setTotal5xxRequest(count500[0]);
        return report;
    }
}
