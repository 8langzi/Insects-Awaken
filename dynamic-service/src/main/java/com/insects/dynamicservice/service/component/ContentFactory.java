package com.insects.dynamicservice.service.component;

import com.insects.dynamicservice.bo.Info;
import com.insects.dynamicservice.config.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ContentFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Qualifier("dynamicServiceMethod")
    @Autowired
    private Map<String, List<Mapper>> typeMapper;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public List<Info> noticeInfo() {
        String type = "1";
        List<Info> infos = new ArrayList<>();
        if (typeMapper.containsKey(type)) {
            List<Mapper> mapperList = typeMapper.get(type);
            List<CompletableFuture<List<Info>>> futureList = new ArrayList<>(infos.size());
            // 执行方法
            mapperList.stream().forEach(o -> {
                Object object = applicationContext.getBean(o.getClassName());
                CompletableFuture<List<Info>> completableFuture = CompletableFuture.supplyAsync(() ->
                        (List<Info>) ReflectionUtils.invokeMethod(o.getMethod(), object), threadPoolExecutor);
                futureList.add(completableFuture);
            });

            CompletableFuture<Void> allDoneFuture = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));
            CompletableFuture<List<Info>> results = allDoneFuture
                    .thenApply(v -> futureList.stream()
                            .map(CompletableFuture::join)
                            .flatMap(o -> o.stream())
                            .collect(Collectors.toList()));
            infos = getFutureResults(results);
        }
        return infos;
    }

    private static List<Info> getFutureResults(CompletableFuture<List<Info>> results) {
        List<Info> infos = null;
        try {
            infos = results.get();
        } catch (InterruptedException e) {
            log.info("InterruptedException: {}", e.getMessage());
        } catch (ExecutionException e) {
            log.info("ExecutionException: {}", e.getMessage());
        }

        return infos;
    }

}