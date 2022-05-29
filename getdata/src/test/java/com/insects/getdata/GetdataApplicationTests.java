package com.insects.getdata;

import com.insects.getdata.service.securitiesassociationofchina.SecuritiesAssociationOfChinaServiceImpl;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootTest
@ComponentScan("com.insects")
@MapperScan(basePackages = { "com.insects" })
class GetdataApplicationTests {

    @Autowired
    private SecuritiesAssociationOfChinaServiceImpl securitiesAssociationOfChinaService;

    @Test
    void contextLoads() {
    }

    @Test
    void getCompanyData() {
        try {
            securitiesAssociationOfChinaService.processAcquiredCompanyData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getEmployeeData(){
        try {
            securitiesAssociationOfChinaService.processAcquiredEmployeeData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getEmployeeLeaderData(){
        try {
            securitiesAssociationOfChinaService.processAcquiredEmployeeLeaderData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getRPIID(){
        securitiesAssociationOfChinaService.processEmployeeDetailRelationByPPPID();
    }

    @Test
    public void getEmployeeDetailRecord(){
        securitiesAssociationOfChinaService.processEmployeeDetailRecordByRPIID();
    }


}
