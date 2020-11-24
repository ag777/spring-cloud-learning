package org.example.demo.customer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Primary    //顶掉base-web-spring-boot-starter中的拦截
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        if(mySecurityProperties.getCsrfDisabled()){
//            http = http.csrf().disable();
//        }
//        http.addFilterBefore(
//                myAuthenticationTokenFilter(),
//                UsernamePasswordAuthenticationFilter.class
//        );
//
//        //RBAC权限控制级别的接口权限校验
//        http.authorizeRequests().anyRequest()
//                .access("@rabcService.hasPermission(request,authentication)");
        http.csrf().disable();
    }
}
