package com.zgm.cloud.controller;

import com.zgm.cloud.entity.CommonResult;
import com.zgm.cloud.entity.Product;
import com.zgm.cloud.service.ProductService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/subtractProduct")
    public CommonResult<Integer> subtractProduct(@RequestParam(value = "productId") Long productId) {
        String key = "dec_store_lock_" + productId;
        RLock lock = redissonClient.getLock(key);
        Product product = productService.getProduct(productId);
        lock.lock(5, TimeUnit.SECONDS);
        try {
            Integer num = product.getNum();
            if (num==0) {
                return new CommonResult<>(444, "失败，不能小于0", null);
            }
            productService.subtractProduct(num-1,productId);
        } finally {
            lock.unlock();
        }
        return new CommonResult<>(444, "成功", null);
    }
}