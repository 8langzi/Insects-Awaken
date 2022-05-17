package com.insects.dynamicservice.controller;

import com.insects.dynamicservice.service.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TypeController {

    @Autowired
    private StrategyService strategyService;

    @PostMapping("/test")
    public void test() {
        strategyService.printAllResult();
    }
}
