package com.zgm.cloud;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description
 * @Author Mr. Zhang
 * @Date 2020/3/6 09:53
 * @Website https://www.zhangguimin.cn
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoDataSourceProxy
public class Consumer8001 {

    public static void main(String[] args) {
        SpringApplication.run(Consumer8001.class, args);
    }
}
