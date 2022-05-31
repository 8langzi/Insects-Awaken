package com.insects.common.remote.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Configuration
@Component
public class RemoteConfig {

    private RemoteProperties remoteProperties;

    private static Map<String,String> serverNameToInsectsPath = new HashMap<>();

    @Autowired
    RemoteConfig(RemoteProperties remoteProperties){
        this.remoteProperties = remoteProperties;
    }

    @Bean("insectsRemotePaths")
    public Map<String,String> insectsRemotePath(){
        List<String> paths = remoteProperties.getServiceMapping();
        if(paths != null && paths.size() != 0){
            Iterator iterator = paths.iterator();
            while (iterator.hasNext()){
                String mapping = (String) iterator.next();
                String[] splitMapping = mapping.split("\\|");
                serverNameToInsectsPath.put(splitMapping[0],splitMapping[1]);
            }
        }
        return serverNameToInsectsPath;
    }

    @Bean("insectsServerPath")
    public Map<String, Map<String, String>> insectsServer(){
        return remoteProperties.getServerMapping();
    }

}
