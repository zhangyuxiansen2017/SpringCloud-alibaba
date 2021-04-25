package com.zgm.cloud.dao;

import com.zgm.cloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description
 * @Author Mr. Zhang
 * @Date 2020/3/6 09:57
 * @Website https://www.zhangguimin.cn
 */
@Mapper
public interface PaymentDao {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
