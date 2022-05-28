package com.insects.getdata.service.baseServiceImpl;

import com.insects.getdata.domain.EmployeeDetailRecord;
import com.insects.getdata.mapper.EmployeeDetailRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeDetailRecordServiceImpl {

    @Autowired
    private EmployeeDetailRecordMapper employeeDetailRecordMapper;

    public void addOne(EmployeeDetailRecord employeeDetailRecord){
        employeeDetailRecordMapper.addOne(employeeDetailRecord);
    }

    public List<String> getAllRPIID(){
        return employeeDetailRecordMapper.getAllRPIID();
    }
}
