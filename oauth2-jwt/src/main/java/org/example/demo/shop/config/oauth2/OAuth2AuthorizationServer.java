package org.example.demo.shop.config.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAuthorizationServer  //开启认证服务器功能。
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Resource
    PasswordEncoder passwordEncoder;

    //密码模式以及客户端模式使用
    @Resource
    private AuthenticationManager authenticationManager;

    //刷新令牌需要
    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private DataSource dataSource;

    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    @Resource
    private TokenEnhancer jwtTokenEnhancer;
    @Resource
    private TokenStore jwtTokenStore;

//    @Bean
//    public TokenStore tokenStore() {
//        //new RedisTokenStore(connectionFactory);   //redis实现
//        return new JdbcTokenStore(dataSource);
//    }

    //这个位置我们将Client客户端注册信息写死，后面章节我们会讲解动态实现
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("c1").secret(passwordEncoder.encode("123456")) // Client 账号、密码。
//                .redirectUris("http://localhost:8088") // 配置回调地址，选填。
//                .authorizedGrantTypes(AuthorizedGrantType.AUTHORIZATION_CODE,AuthorizedGrantType.PASSWORD, AuthorizedGrantType.IMPLICIT, AuthorizedGrantType.CLIENT_CREDENTIALS, AuthorizedGrantType.REFRESH_TOKEN) // 授权码模式
//                .scopes("all") // 可授权的 Scope
//                .accessTokenValiditySeconds(2*60*60)   //令牌过期时间,2小时
//                .refreshTokenValiditySeconds(90*60*60*24);  //刷新令牌过期时间,90天
        //配置客户端存储到db 代替原来得内存模式
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        clientDetailsService.setPasswordEncoder(passwordEncoder);
        clients.withClientDetails(clientDetailsService);
    }


    //密码模式必须
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(jwtTokenStore)
                .authenticationManager(authenticationManager)
            .userDetailsService(userDetailsService);

        //整合JWT
        if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
            System.out.println("集成JWT");
            TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancerList = new ArrayList<>();
            enhancerList.add(jwtTokenEnhancer);
            enhancerList.add(jwtAccessTokenConverter);
            tokenEnhancerChain.setTokenEnhancers(enhancerList);
            //jwt
            endpoints.tokenEnhancer(tokenEnhancerChain)
                    .accessTokenConverter(jwtAccessTokenConverter);
        }
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
//                .tokenKeyAccess("permitAll()")
//                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }


}
