package org.example.demo.other;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * 双数据源, swagger
 */
@SpringBootApplication
@ComponentScan(value = {"org.example.demo"})
@MapperScan(basePackages = {"org.example.demo.**.mapper"})
@EnableCaching
@EnableOpenApi
public class OtherApplication {

    public static void main(String[] args) {
        SpringApplication.run(OtherApplication.class, args);
    }
}
