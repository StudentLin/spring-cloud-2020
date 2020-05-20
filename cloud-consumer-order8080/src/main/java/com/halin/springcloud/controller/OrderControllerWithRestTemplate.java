package com.halin.springcloud.controller;

import com.halin.springcloud.entities.CommonResult;
import com.halin.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderControllerWithRestTemplate {

    // 访问单机版服务
    // public static final String PAYMENT_URL = "http://localhost:8002/provider";

    // 访问集群版服务
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE/provider";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);

    }

}
