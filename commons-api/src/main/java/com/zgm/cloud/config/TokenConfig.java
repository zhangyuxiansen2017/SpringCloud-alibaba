package com.zgm.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Description
 * @Author Mr. Zhang
 * @Date 16/03/2020 14:16
 * @Website https://www.zhangguimin.cn
 */
@Configuration
public class TokenConfig {

    private String SIGNING_KEY = "uaa123";

    /**
     * 生成令牌存储方式
     *
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //对称秘钥，资源服务器使用该秘钥来验证
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }

    /**
     * 生成令牌存储方式
     * @return
     */
    //@Bean
    //public TokenStore tokenStore() {
    //    return new InMemoryTokenStore();
    //}
}
