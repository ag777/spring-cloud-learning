package org.example.demo.customer.config.feign;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    /**
     * NONE	不输出任何日志
     * BASIC	只输出Http 方法名称、请求URL、返回状态码和执行时间
     * HEADERS	输出Http 方法名称、请求URL、返回状态码和执行时间 和 Header 信息
     * FULL	记录Request 和Response的Header，Body和一些请求元数据
     * @return feign日志配置
     */
    @Bean
    public Logger.Level feignLoggerLevel(){
        return  Logger.Level.FULL;
    }
}
