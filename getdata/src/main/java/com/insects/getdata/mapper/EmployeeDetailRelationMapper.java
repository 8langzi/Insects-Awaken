package com.insects.getdata.mapper;


import com.insects.getdata.domain.EmployeeDetailRelation;

import java.util.List;
import java.util.Set;

public interface EmployeeDetailRelationMapper {

    void addOne(EmployeeDetailRelation employeeDetailRelation);

    List<String> getEmployeePPPIDByRelationNotExists();

    List<String> getEmployeeRPIIDByRelationNotExists();

    Set<String> getAllRelationRIPID();
}
