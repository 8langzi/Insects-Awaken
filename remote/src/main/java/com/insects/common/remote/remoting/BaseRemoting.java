package com.insects.common.remote.remoting;

import com.insects.common.remote.utils.HttpServerException;
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
    public <T> T getCall(RestTemplate restTemplate, String url, Object requestBody, Class<T> clazz, HttpHeaders headers) {
        return this.content(restTemplate, url, requestBody, clazz, HttpMethod.GET, headers);
    }

    @Override
    public <T> T postCall(RestTemplate restTemplate, String url, Object requestBody, Class<T> clazz, HttpHeaders headers) {
        return this.content(restTemplate, url, requestBody, clazz, HttpMethod.POST, headers);
    }

    @Override
    public <T> T putCall(RestTemplate restTemplate, String url, Object requestBody, Class<T> clazz, HttpHeaders headers) {
        return this.content(restTemplate, url, requestBody, clazz, HttpMethod.PUT, headers);
    }

    @Override
    public <T> T deleteCall(RestTemplate restTemplate, String url, Object requestBody, Class<T> clazz, HttpHeaders headers) {
        return this.content(restTemplate, url, requestBody, clazz, HttpMethod.DELETE, headers);
    }

    @Override
    public <T> T patchCall(RestTemplate restTemplate, String url, Object requestBody, Class<T> clazz, HttpHeaders headers) {
        return this.content(restTemplate, url, requestBody, clazz, HttpMethod.PATCH, headers);
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
