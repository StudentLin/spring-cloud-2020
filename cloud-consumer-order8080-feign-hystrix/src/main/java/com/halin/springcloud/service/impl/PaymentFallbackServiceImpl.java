package com.halin.springcloud.service.impl;

import com.halin.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackServiceImpl implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "-------------- PaymentFallbackService.paymentInfo_OK : fall back-------------";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-------------- PaymentFallbackService.paymentInfo_TimeOut : fall back-------------";
    }
}
