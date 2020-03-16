package com.zgm.cloud.service.impl;

import com.zgm.cloud.dao.PaymentDao;
import com.zgm.cloud.entity.Payment;
import com.zgm.cloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @Author Mr. Zhang
 * @Date 2020/3/6 09:59
 * @Website https://www.zhangguimin.cn
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
