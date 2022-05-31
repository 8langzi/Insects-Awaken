package com.insects.common.remote.remoting;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Component
public class BaseRemoting implements Remote{

    @Getter
    @Setter
    private HttpHeaders headers;

    @Override
    public <T> T getCall(RestTemplate restTemplate, String url, Class<T> clazz) {
        return this.getCall(restTemplate,url,clazz,null);
    }

    @Override
    public <T> T getCall(RestTemplate restTemplate, String url, Class<T> clazz, HttpHeaders headers) {
        return null;
    }

    @Override
    public <T> T postCall(RestTemplate restTemplate, String url, Class<T> clazz) {
        return this.postCall(restTemplate,url,clazz,null);
    }

    @Override
    public <T> T postCall(RestTemplate restTemplate, String url, Class<T> clazz, HttpHeaders headers) {
        return null;
    }

    public <T> T content(RestTemplate restTemplate, String url, Object requestBody, Class<T> clazz, HttpMethod httpMethodValue,HttpHeaders headers) throws HttpServerErrorException {
        headers = headers == null ? this.headers : headers;
        HttpEntity<Object> requestEntity = new HttpEntity<>(requestBody,headers);
        ResponseEntity<T> responseEntity;
        try {
            responseEntity = restTemplate.exchange(url, httpMethodValue, requestEntity, clazz, new HashMap<>());
        }catch (HttpServerErrorException e){
            throw new HttpServerException(url,e);
        }
        return responseEntity.getBody();
    }
}
