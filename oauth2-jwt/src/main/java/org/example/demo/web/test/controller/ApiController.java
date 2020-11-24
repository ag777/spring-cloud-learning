package org.example.demo.web.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.demo.shop.config.utils.OAuthUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class ApiController {

    @Resource
    private OAuthUtils oauthUtils;

    @RequestMapping(value = "/api/test")
    public String test(OAuth2Authentication auth) {
//        MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        return "api test:"+oauthUtils.getUserId(auth);
    }
}
