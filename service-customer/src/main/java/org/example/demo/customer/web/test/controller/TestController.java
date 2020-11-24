package org.example.demo.customer.web.test.controller;

import org.example.demo.base.model.AjaxResponse;
import org.example.demo.customer.feign.ShopService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Resource
    private ShopService shopService;

    @RequestMapping(value = "/list")
    public AjaxResponse gods() {
        return AjaxResponse.success(shopService.list("客户A"));
    }

    @RequestMapping(value = "/p")
    public AjaxResponse ping() {
        return AjaxResponse.success("啊哈");
    }

    @RequestMapping(value = "/t")
    public String t() {
        return "啊哈";
    }
}
