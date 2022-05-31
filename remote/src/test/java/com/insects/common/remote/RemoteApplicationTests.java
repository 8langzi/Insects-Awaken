package com.insects.common.remote;

import com.insects.common.remote.config.RemoteProperties;
import com.insects.common.remote.remoting.CloudUtils;
import com.insects.common.remote.remoting.RestRemoting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class RemoteApplicationTests {

    @Autowired
    RemoteProperties RemoteProperties;

    @Autowired
    Map<String,String> insectsServicePahs;

    @Autowired
    CloudUtils cloudUtils;

    @Autowired
    RestRemoting restRemoting;

    @Test
    void contextLoads() {
        Object call = restRemoting.getCall("users_timing", null, null, "scan", null);

        System.out.println("1");
    }


    @Test
    public void test(){



    }

}
