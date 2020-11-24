package com.example.demo.config.filter.global;

import com.ag777.util.gson.GsonUtils;
import com.ag777.util.lang.Console;
import com.ag777.util.lang.DateUtils;
import com.ag777.util.lang.IOUtils;
import com.ag777.util.lang.StringUtils;
import com.ag777.util.lang.collection.MapUtils;
import com.example.demo.config.jwt.utils.JwtUtils;
import com.example.demo.config.model.ResponseCodeEnum;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.jwt.crypto.sign.SignatureVerifier;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Configuration
public class AuthorizeGlobalFilter implements GlobalFilter, Ordered {

    @Resource
    private JwtUtils jwtUtils;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        String requestUrl = serverHttpRequest.getURI().getPath();
        //放过部分请求,不需要进行验证
        if(requestUrl.startsWith("/uaa")) {
            return chain.filter(exchange);
        }
        System.out.println(requestUrl);
        String jwtToken = serverHttpRequest
                .getHeaders()
                .getFirst("token");
        if(StringUtils.isBlank(jwtToken)) {
            return error(serverHttpResponse, ResponseCodeEnum.TOKEN_MISSION, "缺少凭证信息");
        }
        try {
            Claims claims = jwtUtils.decode(jwtToken);

            //token过期判断
            if(new Date().after(claims.getExpiration())) {
                return error(serverHttpResponse, ResponseCodeEnum.TOKEN_EXPIRED, "凭证已过期");
            }
            ServerHttpRequest mutableReq = serverHttpRequest
                    .mutate()
                    .header("user_id", MapUtils.getStr(claims, "user_id"))
                    .build();
            ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
            return chain.filter(mutableExchange);
        } catch (IOException ex) {
            ex.printStackTrace();
            return error(serverHttpResponse, ResponseCodeEnum.OTHER, "读取公钥异常");
        } catch (Exception e) {
            e.printStackTrace();
            return error(serverHttpResponse, ResponseCodeEnum.TOKEN_ERROR, "凭证错误");
        }
    }

    private Mono<Void> error(ServerHttpResponse serverHttpResponse, int responseCode, String message) {
        serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
        return getVoidMono(serverHttpResponse, responseCode, message);
    }

    //将JWT鉴权失败的消息响应给客户端
    private Mono<Void> getVoidMono(ServerHttpResponse serverHttpResponse, int responseCode, String message)  {
        serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
        serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
//        AjaxResponse ajaxResponse = AjaxResponse.error(CustomExceptionType.USER_INPUT_ERROR,message);
        DataBuffer dataBuffer = serverHttpResponse.bufferFactory()
                .wrap(GsonUtils.get().toJson(MapUtils.of("success", false, "message", message, "code", responseCode))
                        .getBytes(StandardCharsets.UTF_8));
        return serverHttpResponse.writeWith(Flux.just(dataBuffer));
    }


    @Override
    public int getOrder() {
        return -100;
    }
}
