package org.example.demo.customer.feign;

import org.example.demo.base.model.AjaxResponse;
import org.example.demo.customer.feign.fallback.ShopFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="service-shop", fallback = ShopFallback.class)
public interface ShopService {

    @PostMapping(value = "/api/list")
    AjaxResponse list(@RequestParam("userName") String userName);
}
