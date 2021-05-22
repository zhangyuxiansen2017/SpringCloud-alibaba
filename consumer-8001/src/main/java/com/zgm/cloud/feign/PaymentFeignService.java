package com.zgm.cloud.feign;

import com.zgm.cloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NACOS-PROVIDER", path = "/payment")
public interface PaymentFeignService {
    @PostMapping("/create")
    void create(@RequestBody Payment payment);
}
