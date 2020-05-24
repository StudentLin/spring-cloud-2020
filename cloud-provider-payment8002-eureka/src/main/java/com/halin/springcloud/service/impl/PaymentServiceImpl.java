package com.halin.springcloud.service.impl;

import com.halin.springcloud.dao.PaymentDao;
import com.halin.springcloud.entities.Payment;
import com.halin.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return this.paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        System.out.println("测试测试测试测试");
        return this.paymentDao.getPaymentById(id);
    }
}
