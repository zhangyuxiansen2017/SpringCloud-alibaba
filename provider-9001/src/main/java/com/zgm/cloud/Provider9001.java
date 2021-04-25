package com.zgm.cloud;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description
 * @Author Mr. Zhang
 * @Date 2020/3/6 09:53
 * @Website https://www.zhangguimin.cn
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableAutoDataSourceProxy
public class Provider9001 {

    public static void main(String[] args) {
        SpringApplication.run(Provider9001.class, args);
    }
}
