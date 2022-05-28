package com.insects.getdata.mapper;

import com.insects.getdata.domain.EmployeeDetailRecord;

import java.util.List;

public interface EmployeeDetailRecordMapper {

    void addOne(EmployeeDetailRecord employeeDetailRecord);

    List<String> getAllRPIID();

}
