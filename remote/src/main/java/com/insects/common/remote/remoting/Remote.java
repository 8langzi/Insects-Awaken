package com.insects.common.remote.remoting;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public interface Remote {

    <T> T getCall(RestTemplate restTemplate, String url,Object requestBody, Class<T> clazz, HttpHeaders headers);

    <T> T postCall(RestTemplate restTemplate, String url,Object requestBody, Class<T> clazz, HttpHeaders headers);

    <T> T putCall(RestTemplate restTemplate, String url,Object requestBody, Class<T> clazz, HttpHeaders headers);

    <T> T deleteCall(RestTemplate restTemplate, String url,Object requestBody, Class<T> clazz, HttpHeaders headers);

    <T> T patchCall(RestTemplate restTemplate, String url,Object requestBody, Class<T> clazz, HttpHeaders headers);

}
