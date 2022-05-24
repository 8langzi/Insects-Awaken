package com.insects.getdata;

import com.insects.getdata.service.SecuritiesAssociationOfChinaServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

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
    void getData() {
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

}
