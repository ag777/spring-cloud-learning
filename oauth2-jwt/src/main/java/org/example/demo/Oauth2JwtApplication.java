package org.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages={"org.example.demo"})
@MapperScan(basePackages = {"org.example.demo.base", "org.example.demo"})
@ComponentScan(basePackages = {"org.example.demo.base", "org.example.demo"})
public class Oauth2JwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2JwtApplication.class, args);
    }

}
