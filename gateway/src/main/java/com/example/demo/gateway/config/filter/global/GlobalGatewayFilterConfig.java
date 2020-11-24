package com.example.demo.gateway.config.filter.global;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

/**
 * 统计api响应时长
 */
@Configuration
public class GlobalGatewayFilterConfig {
    @Bean
    @Order(-100)
    public GlobalFilter elapsedGlobalFilter() {
        return (exchange, chain) -> {
            //获取请求处理之前的时间
            Long startTime = System.currentTimeMillis();
            //请求处理完成之后
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                //获取请求处理之后的时间
                Long endTime = System.currentTimeMillis();
                //这里可以将结果进行持久化存储，我们暂时简单处理打印出来
                System.out.println(
                        exchange.getRequest().getURI().getRawPath() +
                                ", cost time : "
                                + (endTime - startTime) + "ms");
            }));
        };
    }

}
