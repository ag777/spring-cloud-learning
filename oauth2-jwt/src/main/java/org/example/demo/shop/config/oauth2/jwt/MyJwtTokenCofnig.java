package org.example.demo.shop.config.oauth2.jwt;

import com.ag777.util.lang.IOUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * 实现jwt令牌配置
 */
@Configuration
public class MyJwtTokenCofnig {
    @Bean(name="jwtTokenStore")
    public TokenStore tokenStore(){
        //注入token store
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    //用于JWT令牌生成，需要设置用于签名解签名的secret密钥
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();

//        KeyPair keyPair = new KeyStoreKeyFactory
//                (keyProperties.getKeyStore().getLocation(), keyProperties.getKeyStore().getSecret().toCharArray())
//                .getKeyPair(keyProperties.getKeyStore().getAlias(),keyProperties.getKeyStore().getPassword().toCharArray());
//        converter.setKeyPair(keyPair);


        KeyStoreKeyFactory storeKeyFactory = new KeyStoreKeyFactory(
                new ClassPathResource("ag777-jwt.jks"), "123123".toCharArray());
        accessTokenConverter.setKeyPair(storeKeyFactory.getKeyPair("ag777-jwt"));

//        accessTokenConverter.setSigningKey("123");
        return accessTokenConverter;
    }

    //用于向令牌中写入一些额外内容
    @Bean
    @ConditionalOnMissingBean(name = "jwtTokenEnhancer")
    public TokenEnhancer jwtTokenEnhancer(){
        return new MyJwtTokenEnhancer();
    }

}
