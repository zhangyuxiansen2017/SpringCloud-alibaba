package com.zgm.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description
 * @Author Mr. Zhang
 * @Date 08/04/2020 23:16
 * @Website https://www.zhangguimin.cn
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ThirdPartyMian9003 {

    public static void main(String[] args) {
        SpringApplication.run(ThirdPartyMian9003.class, args);
    }
}
