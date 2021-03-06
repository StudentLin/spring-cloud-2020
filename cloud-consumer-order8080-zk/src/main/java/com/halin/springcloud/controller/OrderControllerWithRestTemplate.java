package com.halin.springcloud.controller;

import com.halin.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderControllerWithRestTemplate {

    // 访问单机版服务
    // public static final String PAYMENT_URL = "http://localhost:8002/provider";

    // zookeeper服务名称区分大小写
    public static final String PAYMENT_URL = "http://cloud-payment-service/provider";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/payment/create")
    public String create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/zk", payment, String.class);
    }

}
