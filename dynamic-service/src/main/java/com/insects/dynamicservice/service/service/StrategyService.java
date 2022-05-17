package com.insects.dynamicservice.service.service;

import com.insects.dynamicservice.service.component.ContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyService {


    @Autowired
    private ContentFactory contentFactory;

    public void printAllResult() {
        List<Info> infos = contentFactory.noticeInfo();
        infos.stream().forEach(o -> {
            System.out.println(o);
        });
    }
}
