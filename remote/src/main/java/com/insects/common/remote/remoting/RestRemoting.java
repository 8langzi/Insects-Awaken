package com.insects.common.remote.remoting;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("restRemoting")
public class RestRemoting extends BaseRemoting {

    private final CloudUtils cloudUtils;

    public RestRemoting(CloudUtils cloudUtils){
        this.cloudUtils = cloudUtils;
    }

    public <T> T getCall(String serverName, Map requestBody, Class<T> clazz, String resourcePath, HttpHeaders headers){
        String url = cloudUtils.getUrl(serverName, resourcePath, requestBody);
        return super.getCall(cloudUtils.getRestTemplate(),url,requestBody,clazz,headers);
    }

    public <T> T postCall(String serverName, Map requestBody, Class<T> clazz, String resourcePath, HttpHeaders headers){
        String url = cloudUtils.getUrl(serverName, resourcePath, requestBody);
        return super.postCall(cloudUtils.getRestTemplate(),url,requestBody,clazz,headers);
    }

}
