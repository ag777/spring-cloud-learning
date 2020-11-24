package org.example.demo.customer.feign.fallback;

import org.example.demo.base.exception.CustomExceptionType;
import org.example.demo.base.model.AjaxResponse;
import org.example.demo.customer.feign.ShopService;
import org.springframework.stereotype.Component;

@Component
public class ShopFallback implements ShopService {
    @Override
    public AjaxResponse list(String userName) {
        return AjaxResponse.success("啊啊啊");
//        return AjaxResponse.error(CustomExceptionType.SYSTEM_ERROR
//                ,"调用获取商品列表接口失败!");
    }
}
