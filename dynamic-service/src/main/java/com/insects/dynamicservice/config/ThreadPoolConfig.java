package com.insects.dynamicservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPoolConfig {

    @Bean
    @Primary
    public ThreadPoolExecutor threadPoolExecutor() {
        return new ThreadPoolExecutor(2 * Runtime.getRuntime().availableProcessors(), 1024, 60L, TimeUnit.MILLISECONDS,
                new SynchronousQueue(), new CustomizableThreadFactory("my-threadpoll-"));
    }
}
