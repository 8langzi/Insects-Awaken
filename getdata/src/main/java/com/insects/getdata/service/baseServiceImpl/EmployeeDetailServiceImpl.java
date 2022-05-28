package com.insects.getdata.service.baseServiceImpl;

import com.insects.getdata.domain.EmployeeDetail;
import com.insects.getdata.mapper.EmployeeDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeDetailServiceImpl {

    @Autowired
    private EmployeeDetailMapper employeeDetailMapper;

    public void addEmploeeDetail(List<EmployeeDetail> employeeDetails){
        employeeDetailMapper.addEmployeeDetail(employeeDetails);
    }

    public List<EmployeeDetail> getAll(){
        return employeeDetailMapper.getAll();
    }

    public void addOne(EmployeeDetail employeeDetail){
        employeeDetailMapper.addOne(employeeDetail);
    }

    public List<String> getAllRIPID(){
        return employeeDetailMapper.getAllRIPID();
    }
}