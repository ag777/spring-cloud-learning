package com.example.demo.gateway.config;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.function.Predicate;

@Component
public class RbacAuthRoutePredicateFactory extends AbstractRoutePredicateFactory<RbacAuthRoutePredicateFactory.Config> {
    public RbacAuthRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange -> {
            String requestURI = exchange.getRequest().getURI().getPath();
//            System.out.println(requestURI);
            if (config.getFlag().equals("rbac")
                    &&(requestURI.startsWith("/")
                    ||requestURI.startsWith("/sysuser")
                    ||requestURI.startsWith("/sysorg")
                    ||requestURI.startsWith("/sysrole")
                    ||requestURI.startsWith("/sysmenu")
                    ||requestURI.startsWith("/sysdict")
                    ||requestURI.startsWith("/sysapi"))) {

                return true;  //表示匹配成功
            }
            return false;   //表示匹配失败
        };
    }


    //自定义参数args配置类
    public static class Config {
        private String flag; //该参数对应配置文件的args

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }
    }
}
