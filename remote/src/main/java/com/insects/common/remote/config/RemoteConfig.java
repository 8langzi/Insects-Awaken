package com.insects.common.remote.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

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

    @Bean(name = "restTemplate")
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        return getRestTemplate(requestFactory);
    }

    private RestTemplate getRestTemplate(SimpleClientHttpRequestFactory requestFactory) {
        String readTimeout = Optional.ofNullable("600").orElse("60000");
        String connectTimeout = Optional.ofNullable("600").orElse("60000");
        requestFactory.setConnectTimeout(Integer.valueOf(connectTimeout));
        requestFactory.setReadTimeout(Integer.valueOf(readTimeout));
        RestTemplate restTemplate = new RestTemplate(requestFactory);
//        HttpHeaderInterceptor hhi = new HttpHeaderInterceptor();
//        restTemplate.getInterceptors().add(hhi);
        return restTemplate;
    }

}
