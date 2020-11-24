package org.example.demo.shop.web.api.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Service
@RefreshScope //这里需要加上RefreshScope注解,结合ConfigurationProperties一起使用
public class ApiService {
    @Value("${system.testMsg}")
    private String testMsg;

    @SentinelResource("getTestMsg")
    public String getTestMsg() {
        return testMsg;
    }
}
