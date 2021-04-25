package com.zgm.cloud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

/**
 * @Description
 * @Author Mr. Zhang
 * @Date 15/03/2020 18:21
 * @Website https://www.zhangguimin.cn
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    public static final String RESOURCE_ID = "res1";
    /**
     * tokenStore、accessTokenConverter两个bean配置在公共服务中，在资源服务器也需要使用
     */
    @Resource
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID)
                .tokenStore(tokenStore)
                //.tokenServices(tokenService())//使用JWT，屏蔽远程调用验证
                .stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**")
                .access("#oauth2.hasAnyScope('ROLE_ADMIN','ROLE_USER','ROLE_API')")
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    //资源服务令牌解析服务，在使用JWT后不需要远程验证
    //@Bean
    //public ResourceServerTokenServices tokenService() {
    //    //使用远程服务请求授权服务器校验token,必须指定校验token 的url、client_id，client_secret
    //    RemoteTokenServices service = new RemoteTokenServices();
    //    service.setCheckTokenEndpointUrl("http://localhost:9999/oauth/check_token");
    //    service.setClientId("c1");
    //    service.setClientSecret("secret");
    //    return service;
    //}
}