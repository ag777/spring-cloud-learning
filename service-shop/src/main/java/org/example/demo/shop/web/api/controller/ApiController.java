package org.example.demo.shop.web.api.controller;

import com.ag777.util.lang.collection.ListUtils;
import org.example.demo.base.model.AjaxResponse;
import org.example.demo.shop.web.api.service.ApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")

public class ApiController {
    @Resource
    private ApiService apiService;


    @RequestMapping(value = "/list")
    public AjaxResponse list(@RequestParam("userName") String userName) {
        System.out.println(userName);
        int a = 1/0;    //制造异常
        return AjaxResponse.success(ListUtils.of("商品A", "商品B", "商品C"));
    }

    @RequestMapping(value = "/test")
    public AjaxResponse test() {
        return AjaxResponse.success(ListUtils.of("商品A", "商品B", "商品C"));
    }

    @GetMapping(value = "/testMsg")
//    @SentinelResource(
//            value = "api_testMsg",
//            blockHandler = "blockHandler",
//            blockHandlerClass = {MyBlockExceptionHandler.class})
    public AjaxResponse testMsg() {
        return AjaxResponse.success(apiService.getTestMsg());
    }
}
