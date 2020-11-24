package org.example.demo.shop.config.oauth2.jwt;

import org.example.demo.base.model.MyUserDetails;
import org.example.demo.shop.config.oauth2.model.OAuthTokenExtraKeys;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现向jwt令牌中写额外信息
 */
public class MyJwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
                                     OAuth2Authentication authentication) {
        Map<String, Object> info = new HashMap<>();
        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        if(user != null) {
            info.put(OAuthTokenExtraKeys.USER_ID, user.getUserId());
            //模拟通过用户编号查询数据
            info.put("test_data", "假装通过用户编号获取数据:"+user.getUserId());//扩展信息
        }
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
