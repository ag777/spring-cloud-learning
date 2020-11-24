package com.example.demo.gateway.config.filter.part;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 增加请求参数
 */
@Component
@Order(99)
public class IPForbidGatewayFilterFactory
        extends AbstractGatewayFilterFactory<IPForbidGatewayFilterFactory.Config> {

    public IPForbidGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("permitIp");  //对应config类的参数
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            //获取服务访问的客户端ip
            InetSocketAddress address = exchange.getRequest().getRemoteAddress();
            if (address == null) {
                //获取不到ip, 拒绝请求
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }

            //地址匹配配置文件，则放过
            String ip = address.getAddress().getHostAddress();
            //如果ip为本地地址，则放过
            String[] localIps = {"127.0.0.1", "0:0:0:0:0:0:0:1"};
            for (String localIp : localIps) {
                if(localIp.equals(ip)) {
                    return chain.filter(exchange);
                }
            }

            String[] ips = config.getPermitIps();
            for (String ip1 : ips) {
                if (ip1.equals(ip)) {
                    //如果客户端ip=permitIp，继续执行过滤器链允许继续访问
                    return chain.filter(exchange);
                }
            }
            //否则返回，拒绝请求
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        };
    }

    static public class Config {
        //参数用数组或者list接都行
        private String[] permitIps;

        public String[] getPermitIps() {
            return permitIps;
        }

        //必须有set方法
        public void setPermitIps(String[] permitIps) {
            this.permitIps = permitIps;
        }
    }

}