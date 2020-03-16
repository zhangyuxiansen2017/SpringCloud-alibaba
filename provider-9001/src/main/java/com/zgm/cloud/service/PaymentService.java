package com.zgm.cloud.service;

import com.zgm.cloud.entity.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @Description
 * @Author Mr. Zhang
 * @Date 2020/3/6 09:58
 * @Website https://www.zhangguimin.cn
 */
public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}