package org.example.demo.shop.config.oauth2.model;

public class AuthorizedGrantType {
    private AuthorizedGrantType() {
    }

    /**
     * 授权码模式
     *
     * <p>/oauth/authorize
     * [参数]没说明在AuthorizationServerConfigurerAdapter中配置
     * response_type=code 固定
     * client_id
     * redirect_uri
     *
     * <p>/oauth/token
     * [参数]
     * grant_type=authorization_code 固定
     * code 之前接口返回
     * client_id
     * client_secret
     *
     * [返回]
     * {
     *   "access_token": "15b4fd1c-c540-43e7-b859-69682b5a1871",
     *   "token_type": "bearer",
     *   "expires_in": 42752,
     *   "refresh_token": "c66bcbb7-a0f9-4226-8217-e5fce6eef13c", //开启刷新令牌额外返回
     *   "scope": "all"
     * }
     *
     * 其中expires_in单位是秒,下不赘述
     */
    public static final String AUTHORIZATION_CODE = "authorization_code";

    /**
     * 简单模式(简化的授权码莫斯,通过url，登陆后直接返回密码)
     *
     * <p>在授权码模式的基础上增加配置authorizedGrantTypes,key为implicit
     * 访问链接中的参数response_type改为token
     * 直接get返回token
     */
    public static final String IMPLICIT = "implicit";

    /**
     * 密码模式
     *
     * <p>前提:在WebSecurityConfigurerAdapter中注入bean: AuthenticationManager
     *        @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
     *     @Override
     *     protected AuthenticationManager authenticationManager() throws Exception {
     *         return super.authenticationManager();
     *     }
     *
     * 在AuthorizationServerConfigurerAdapter中进行配置
     *    @Override
     *     public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
     *         endpoints.authenticationManager(authenticationManager);
     *     }
     * 否则会报Unsupported grant type: password
     *
     * <p>/oauth/token
     * [参数]
     * grant_type=password 固定
     * username,password 用户名/密码在 WebSecurityConfigurerAdapter 中配置
     * ***header中需要放置Authorization,值为Basic XXX,其中xxx为client_id:client_secret base64后的串，这两个变量在AuthorizationServerConfigurerAdapter中配置
     *
     * [返回]
     * {
     *   "access_token": "e490e739-81e3-4eae-add0-5874a53a41ea",
     *   "token_type": "bearer",
     *   "expires_in": 41967,
     *   "refresh_token": "c66bcbb7-a0f9-4226-8217-e5fce6eef13c", //开启刷新令牌额外返回
     *   "scope": "all"
     * }
     */
    public static final String PASSWORD = "password";



    /**
     * 客户端模式(简化的密码模式, 不需要用户名密码)
     *
     * <p>/oauth/token
     * [参数]
     * 同密码模式,不需要username,password这两个参数
     * grant_type传client_credentials
     *
     * [返回]
     * {
     *   "access_token": "15b4fd1c-c540-43e7-b859-69682b5a1871",
     *   "token_type": "bearer",
     *   "expires_in": 43199,
     *   "scope": "all"
     * }
     */
    public static final String CLIENT_CREDENTIALS = "client_credentials";

    /**
     * 配置开启刷新令牌
     *
     * <p>/oauth/token
     * 前提:客户端模式和简化模式是不支持refresh_token
     * 需要配置endpoints.userDetailsService(userDetailsService),否则前台会拿到Internal Server Error, 后台会报:UserDetailsService is required
     *
     * [参数]
     * grant_type=refresh_token 固定
     * refresh_token 由之前接口得到
     * ***和密码模式一样,header中需要放置Authorization,值为Basic XXX,其中xxx为client_id:client_secret base64后的串，这两个变量在AuthorizationServerConfigurerAdapter中配置
     *
     * [返回]
     * {
     *   "access_token": "41238653-f49b-464c-ac2a-57132fb0c420",
     *   "token_type": "bearer",
     *   "refresh_token": "48c5f350-861f-4d22-831a-f6d1b2340512",
     *   "expires_in": 43199,
     *   "scope": "all"
     * }
     */
    public static final String REFRESH_TOKEN = "refresh_token";
}
