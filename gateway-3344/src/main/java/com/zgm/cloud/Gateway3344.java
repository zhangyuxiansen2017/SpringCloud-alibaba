package com.zgm.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description
 * @Author Mr. Zhang
 * @Date 16/03/2020 22:42
 * @Website https://www.zhangguimin.cn
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Gateway3344 {
    public static void main(String[] args) {
        SpringApplication.run(Gateway3344.class, args);
    }
}
