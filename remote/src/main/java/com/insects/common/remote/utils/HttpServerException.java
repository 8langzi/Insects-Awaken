package com.insects.common.remote.utils;

import org.springframework.web.client.HttpServerErrorException;

public class HttpServerException extends RuntimeException {

    private String url;

    private HttpServerErrorException httpServerErrorException;

    public HttpServerException(String url,HttpServerErrorException httpServerErrorException){
        this.url = url;
        this.httpServerErrorException = httpServerErrorException;
    }

    public String getUrl() {
        return url;
    }

    public HttpServerErrorException getHttpServerErrorException() {
        return httpServerErrorException;
    }

}
