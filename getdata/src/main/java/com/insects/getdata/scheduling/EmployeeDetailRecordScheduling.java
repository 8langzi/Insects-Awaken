package com.insects.getdata.scheduling;

import com.insects.getdata.service.securitiesassociationofchina.SecuritiesAssociationOfChinaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class EmployeeDetailRecordScheduling {

    @Autowired
    private SecuritiesAssociationOfChinaServiceImpl securitiesAssociationOfChinaService;

    AtomicLong index = new AtomicLong(0);

    @Scheduled(fixedDelay = 5000)
    public void getEmployeeDetailRecord(){
        System.out.println("this is the  : " + index.incrementAndGet() + " time");
        securitiesAssociationOfChinaService.processEmployeeDetailRecordByRPIID();
    }
}
