package com.example.demo.gateway.config.filter.part;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Collections;
import java.util.List;

/**
 * ip过滤
 * <p>
 * 局部过滤器以xxxGatewayFilterFactory命名，其中xxx为过滤器名称，写入配置文件进行拦截
 */
@Component
@Order(99)
public class AddParamGatewayFilterFactory
        extends AbstractGatewayFilterFactory<AddParamGatewayFilterFactory.Config> {

    public AddParamGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("permitIp");  //对应config类的参数
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            URI uri = exchange.getRequest().getURI();
            StringBuilder query = new StringBuilder();
            String originalQuery = uri.getRawQuery();

            if (StringUtils.hasText(originalQuery)) {
                query.append(originalQuery);
                if (originalQuery.charAt(originalQuery.length() - 1) != '&') {
                    query.append('&');
                }
            }

            query.append("wd=").append(config.getValue());

            try {
                URI newUri = UriComponentsBuilder.fromUri(uri)
                        .replaceQuery(query.toString())
                        .build(true)
                        .toUri();
                System.out.println("newUri:"+newUri+"\t"+query.toString());
                ServerHttpRequest request = exchange.getRequest().mutate().uri(newUri).build();

                return chain.filter(exchange.mutate().request(request).build());
            } catch (RuntimeException ex) {
                throw new IllegalStateException("Invalid URI query: \"" + query.toString() + "\"");
            }
        };
    }

    static public class Config {
        //参数用数组或者list接都行
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}