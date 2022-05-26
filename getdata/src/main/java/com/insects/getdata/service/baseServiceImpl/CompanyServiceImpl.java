package com.insects.getdata.service.baseServiceImpl;

import com.insects.getdata.domain.Company;
import com.insects.getdata.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl {

    @Autowired
    private CompanyMapper companyMapper;

    public void addCompany(List<Company> companies){
        companyMapper.addCompany(companies);
    }

    public List<Company> getAll(){
        return companyMapper.getAll();
    }

    public List<String> getAllAOIID(){
        return companyMapper.getAllAOIID();
    }
}
