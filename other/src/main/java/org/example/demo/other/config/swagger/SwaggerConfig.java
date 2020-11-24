package org.example.demo.other.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ag777
 * @Description swagger配置
 * @Date: 2020-11-20 12:49
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot利用swagger构建api文档")
                .description("简单优雅的restfun风格")
//                .termsOfServiceUrl("http://www.zimug.com")
                .version("1.0")
                .build();
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //扫描basePackage包下面的“/rest/”路径下的内容作为接口文档构建的目标
                .apis(RequestHandlerSelectors.basePackage("org.example.demo"))
                .paths(PathSelectors.any())
//                .paths(PathSelectors.regex("/rest/.*"))
                .build();
    }
}
