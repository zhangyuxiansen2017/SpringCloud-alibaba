package com.zgm.cloud.feign;

import com.zgm.cloud.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@FeignClient(name = "NACOS-CONSUMER", path = "/product")
public interface ProductFeignService {

    @GetMapping("/subtractProduct")
    CommonResult<Integer> subtractProduct();
}