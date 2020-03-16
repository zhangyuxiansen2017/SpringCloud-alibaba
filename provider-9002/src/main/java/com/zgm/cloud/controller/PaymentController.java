package com.zgm.cloud.controller;

import com.zgm.cloud.entity.CommonResult;
import com.zgm.cloud.entity.Payment;
import com.zgm.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;


    @PostMapping("/create")
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入数据的ID:\t" + payment.getId());
        log.info("插入结果：" + result);
        if (result > 0) {
            return new CommonResult<>(200, "插入数据成功,serverPort" + serverPort, result);
        } else {
            return new CommonResult<>(444, "插入数据失败", null);
        }
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("***查询结果：" + payment);
        if (payment != null) {
            return new CommonResult<>(200, "查询数据成功,serverPort" + serverPort+" 配置文件信息："+ payment);
        } else {
            return new CommonResult<>(444, "没有对应记录", null);
        }
    }
}
