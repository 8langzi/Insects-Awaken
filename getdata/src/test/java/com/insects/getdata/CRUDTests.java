package com.insects.getdata;

import com.insects.getdata.domain.Company;
import com.insects.getdata.domain.Employee;
import com.insects.getdata.service.baseServiceImpl.CompanyServiceImpl;
import com.insects.getdata.service.baseServiceImpl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootTest
@ComponentScan("com.insects")
@MapperScan(basePackages = { "com.insects" })
public class CRUDTests {

    @Autowired
    CompanyServiceImpl companyService;

    @Autowired
    EmployeeServiceImpl employeeService;

    @Test
    public void Test(){
        List<Company> companies = companyService.getAll();
        List<Employee> employees = employeeService.getAll();

        System.out.println("company size is === " + companies.size());
        System.out.println("employee size is === " + employees.size());
    }


    @Test
    public void getAllPPPID(){
        List<String> allPPPID = employeeService.getAllPPPID();


        System.out.println(allPPPID.size());
    }

}
