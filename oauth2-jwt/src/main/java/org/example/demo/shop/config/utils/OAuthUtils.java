package org.example.demo.shop.config.utils;

import com.ag777.util.lang.collection.MapUtils;
import org.example.demo.shop.config.oauth2.model.OAuthTokenExtraKeys;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 封装了获取token中额外信息的方法,可以注入到controller层供信息获取
 *
 * @author ag777
 * @version create on 2020年11月12日,last modify at 2020年11月12日
 */
@Component
public class OAuthUtils {

    @Resource
    private TokenStore tokenStore;


    /**
     * 获取用户名称，这个比较特殊，直接通过SecurityContextHolder即可获取字符串类型的username
     * @return username
     */
    public String getUsername() {
        return (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 获取用户编号,需要事先在MyJwtTokenEnhancer中路实现写入
     * @param auth OAuth2Authentication
     * @return user_id
     */
    public Integer getUserId(OAuth2Authentication auth) {
        Map<String, Object> infoMap = getInfoMap(auth);
        return MapUtils.getInt(infoMap, OAuthTokenExtraKeys.USER_ID);
    }


    /**
     *
     * @param auth OAuth2Authentication
     * @return 令牌中的信息map
     */
    public Map<String, Object> getInfoMap(OAuth2Authentication auth) {
        OAuth2AccessToken accessToken = getAccessToken(auth);
        return accessToken.getAdditionalInformation();
    }

    /**
     * 解析OAuth2Authentication对象，获取令牌
     * @param auth OAuth2Authentication
     * @return OAuth2AccessToken
     */
    public OAuth2AccessToken getAccessToken(OAuth2Authentication auth) {
        OAuth2AuthenticationDetails details
                = (OAuth2AuthenticationDetails) auth.getDetails();

        OAuth2AccessToken accessToken = tokenStore
                .readAccessToken(details.getTokenValue());
        return accessToken;
    }
}
