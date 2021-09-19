package com.zgm.cloud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description 缺失这个类将不会校验权限
 * @Author Mr. Zhang
 * @Date 16/03/2020 15:39
 * @Website https://www.zhangguimin.cn
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //安全拦截机制(最重要)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                //.antMatchers("/r/r1")
                //.hasAuthority("p2")
                //.antMatchers("/r/r2")基于注解后可以不需要这配置
                //.hasAuthority("p2")
                .antMatchers("/**")
//                .authenticated()//所有/r/**的请求必须认证通过
//                .antMatchers("/provider/actuator/**")
                .permitAll();//除了/r/**，其它的请求可以访问
    }
}
