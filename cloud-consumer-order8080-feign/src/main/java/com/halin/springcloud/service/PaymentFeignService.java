package com.halin.springcloud.service;


import com.halin.springcloud.entities.CommonResult;
import com.halin.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Component
//指定调用哪个微服务
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

//    为了方便对比，以下是服务端的两个接口
//    public int create(Payment payment);
//
//    public Payment getPaymentById(@Param("id") Long id);

    @GetMapping(value = "/provider/payment/create")
    int create(Payment payment);

    @GetMapping(value = "/provider/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    //测试feign超时
    @GetMapping("/provider/payment/feign/timeout")
    String paymentFeignTimeout();

}
