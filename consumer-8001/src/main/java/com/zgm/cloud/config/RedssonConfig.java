package com.zgm.cloud.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class RedssonConfig {
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() throws IOException {
        return Redisson.create(Config.fromYAML(new ClassPathResource("redisson-single.yml").getInputStream()));
    }
}