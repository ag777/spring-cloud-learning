package org.example.demo.base.config.filter;

import org.example.demo.base.model.AjaxResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Component
@ControllerAdvice(basePackages = "org.example.demo")
public class GlobalResponseAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        //return returnType.hasMethodAnnotation(ResponseBody.class);
        return true;
    }
    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        //对于JSON类型的响应数据
        if(selectedContentType.equalsTypeAndSubtype(
                MediaType.APPLICATION_JSON)){
            if(body instanceof AjaxResponse){
                //如果Controller返回值body的数据类型是AjaxResponse（body instanceof AjaxResponse）
                //就将body直接返回
                response.setStatusCode(HttpStatus.valueOf(
                        ((AjaxResponse) body).getCode())  //将业务异常状态码赋值给HTTP状态码
                );
                return body;
            }else{
                //如果Controller返回值body的数据类型不是AjaxResponse，
                //就将body封装为AjaxResponse类型返回，总之要统一数据响应的类型
                AjaxResponse ajaxResponse = AjaxResponse.success("妈的，我就要转义");
                response.setStatusCode(HttpStatus.valueOf(
                        ajaxResponse.getCode())   //将业务异常状态码赋值给HTTP状态码
                );
                return AjaxResponse.success(body);
            }
        }
        return body;
    }
}
