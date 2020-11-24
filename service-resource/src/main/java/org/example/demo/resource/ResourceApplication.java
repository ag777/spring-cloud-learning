package org.example.demo.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * minio: 界面:http://localhost:9000/ admin/admin123456
 */
@SpringBootApplication
@ComponentScan(value = {"org.example.demo"})
public class ResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }
}
