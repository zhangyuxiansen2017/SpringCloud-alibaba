package com.zgm.cloud.controller;

import com.zgm.cloud.entity.CommonResult;
import com.zgm.cloud.entity.Payment;
import com.zgm.cloud.feign.ProductFeignService;
import com.zgm.cloud.service.PaymentService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author Mr. Zhang
 * @Date 2020/3/6 10:00
 * @Website https://www.zhangguimin.cn
 */
@RestController
@Slf4j
@RequestMapping("/payment")
@RefreshScope
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ProductFeignService productFeignService;

    @Value("${server.port}")
    private String serverPort;


    @PostMapping("/buy")
    @GlobalTransactional(name = "product-server-group", rollbackFor = Exception.class)
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入数据的ID:\t" + payment.getId());
        log.info("插入结果：" + result);
        //扣减数量
        productFeignService.subtractProduct(1L);

//        int i = 10 / 0;
        if (result > 0) {
            return new CommonResult<>(200, "插入数据成功,serverPort" + serverPort, result);
        } else {
            return new CommonResult<>(444, "插入数据失败", null);
        }
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("***查询结果：" + payment);
        if (payment != null) {
            return new CommonResult<>(200, "查询数据成功,serverPort" + serverPort + " 配置文件信息：" + payment);
        } else {
            return new CommonResult<>(444, "没有对应记录", null);
        }
    }
}
