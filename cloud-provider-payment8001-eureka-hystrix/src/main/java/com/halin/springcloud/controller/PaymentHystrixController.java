package com.halin.springcloud.controller;

import com.halin.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentHystrixController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        log.info("*** paymentInfo_OK **  id=" + id);
        return paymentService.paymentInfo_OK(id);
    }

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        log.info("*** paymentInfo_TimeOut **  id=" + id);
        return paymentService.paymentInfo_TimeOut(id);
    }

    //======================= 服务熔断
    @GetMapping(value = "/payment/hystrix/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        log.info("*** paymentInfo_TimeOut **  id=" + id);
        return paymentService.paymentCircuitBreaker(id);
    }

}
