package com.insects.common.remote.remoting;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public interface Remote {

    <T> T getCall(RestTemplate restTemplate,String url,Class<T> clazz);

    <T> T getCall(RestTemplate restTemplate, String url, Class<T> clazz, HttpHeaders headers);

    <T> T postCall(RestTemplate restTemplate,String url,Class<T> clazz);

    <T> T postCall(RestTemplate restTemplate, String url, Class<T> clazz, HttpHeaders headers);

}
