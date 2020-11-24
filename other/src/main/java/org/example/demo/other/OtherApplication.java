package org.example.demo.other;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * swagger,访问地址:http://localhost:8200/swagger-ui/index.html
 * activemq,连接瑞景tcp://localhost:61616, 界面url:http://localhost:8161/admin/topics.jsp,账号密码默认为admin
 * redis缓存:连接端口:6379,可以使用RedisDesktopManager工具进行查看内部数据
 * 双数据源(已注释)
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
