package com.zgm.cloud;

import com.zgm.cloud.entity.Payment;
import com.zgm.cloud.service.PaymentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Provider9001.class})
public class Provider9001Test {
    @Autowired
    private PaymentService paymentService;

    @Test
    public void test_02() {
        Payment payment = paymentService.getPaymentById(9L);
        System.out.println(payment);
    }

    @Test
    public void test_01() {
        Payment payment = new Payment();
        payment.setSerial("测试2");
        paymentService.create(payment);
    }
}

