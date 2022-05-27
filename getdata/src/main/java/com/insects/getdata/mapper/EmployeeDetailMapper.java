package com.insects.getdata.mapper;

import com.insects.getdata.domain.EmployeeDetail;

import java.util.List;

public interface EmployeeDetailMapper {

    void addEmployeeDetail(List<EmployeeDetail> employeeDetails);

    List<EmployeeDetail> getAll();

    void addOne(EmployeeDetail employeeDetail);
}
