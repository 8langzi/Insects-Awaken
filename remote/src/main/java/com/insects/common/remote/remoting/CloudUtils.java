package com.insects.common.remote.remoting;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
@DependsOn({"insectsRemotePaths"})
public class CloudUtils {

    private final Map<String,String> insectsRemotePaths;

    private final Map<String,Map<String,String>> insectsServerPath;

    private final RestTemplate restTemplate;

    public CloudUtils(@Qualifier("insectsRemotePaths") Map<String,String> insectsRemotePaths,
               @Qualifier("insectsServerPath") Map<String,Map<String,String>> insectsServerPath,
               @Qualifier("restTemplate") RestTemplate restTemplate){
        this.insectsRemotePaths = insectsRemotePaths;
        this.insectsServerPath = insectsServerPath;
        this.restTemplate = restTemplate;
    }

    public String genUrl(String protocol, String serviceName, Map requestParam, String resourcePath){
        StringBuilder url = new StringBuilder(protocol);
        String remotePath = insectsRemotePaths.get(serviceName);
        if(remotePath == null || serviceName.equals("")){
            remotePath = serviceName;
        }

        if(remotePath != null){
            Map<String, String> pathConfig = insectsServerPath.get(remotePath.split("/")[0]);
            if(pathConfig != null){
                String domainname = pathConfig.get("domainname");
                String port = pathConfig.get("port");
                url.append(domainname).append(":").append(port).append("/");
            } else {
                // todo 报错
            }
        }
        if(resourcePath == null || resourcePath.length() == 0){
            url.append(remotePath);
        } else {
            url.append(remotePath).append("/").append(String.join("/", resourcePath));
        }
        // todo 添加参数
//        this.dealRequestParam();
        return url.toString();
    }

    public String getUrl(String serviceName, String resourcePath, Map requestParam){
        return genUrl("http://", serviceName, requestParam,resourcePath);
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
