package com.insects.getdata.service.baseServiceImpl;


import com.insects.getdata.domain.Employee;
import com.insects.getdata.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl {

    @Autowired
    private EmployeeMapper employeeMapper;

    public void addEmploee(List<Employee> employees){
        employeeMapper.addEmploee(employees);
    }

}
