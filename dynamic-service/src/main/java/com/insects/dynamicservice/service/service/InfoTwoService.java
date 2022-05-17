package com.insects.dynamicservice.service.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class InfoTwoService extends AbstractServiceContent {

    @Type(type = "3,4,5")
    public List<Info> getInfo() {
        List<Info> infos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Info info = Info.builder().name((char) ('a' + i) + "").content(new Random().toString()).build();
            infos.add(info);
        }
        return infos;
    }
}
