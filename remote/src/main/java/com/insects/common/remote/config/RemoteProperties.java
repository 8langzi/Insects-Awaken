package com.insects.common.remote.config;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "web.remote")
@Component
@Data
@Slf4j
public class RemoteProperties {

    private List<String> serviceMapping = new ArrayList<>();

    private Map<String,Map<String,String>> serverMapping = new HashMap<>();
}