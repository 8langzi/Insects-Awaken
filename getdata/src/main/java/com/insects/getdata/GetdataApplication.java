package com.insects.getdata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan("com.insects")
@MapperScan(basePackages = { "com.insects" })
public class GetdataApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetdataApplication.class, args);
    }

}
