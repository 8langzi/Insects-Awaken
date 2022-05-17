package com.insects.dynamicservice.service.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class InfoOneService extends AbstractServiceContent {

    @Type(type = "1,2,3")
    public List<Info> getInfo() {
        List<Info> infos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Info info = Info.builder().name(i + "").content(new Random().toString()).build();
            infos.add(info);
        }
        return infos;
    }
}