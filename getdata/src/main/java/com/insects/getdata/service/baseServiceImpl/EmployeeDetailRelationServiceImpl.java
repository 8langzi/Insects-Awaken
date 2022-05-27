package com.insects.getdata.service.baseServiceImpl;

import com.insects.getdata.domain.EmployeeDetailRelation;
import com.insects.getdata.mapper.EmployeeDetailRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeDetailRelationServiceImpl {

    @Autowired
    private EmployeeDetailRelationMapper employeeDetailRelationMapper;

    public void addOne(EmployeeDetailRelation employeeDetailRelation){
        employeeDetailRelationMapper.addOne(employeeDetailRelation);
    }
}