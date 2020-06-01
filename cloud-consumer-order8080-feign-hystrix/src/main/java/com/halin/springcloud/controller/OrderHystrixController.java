package com.halin.springcloud.controller;


import com.halin.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping(value = "consumer")
// 全局默认hystrix fallback降级方案
@DefaultProperties(defaultFallback = "payment_Global_FallbackHandler")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        log.info("*** paymentInfo_OK **  id=" + id);
        return paymentHystrixService.paymentInfo_OK(id);
    }

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "9000")
//    })
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        log.info("*** paymentInfo_OK **  id=" + id);
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "客户端: " + Thread.currentThread().getName() + " paymentInfo_TimeOutHandler " + id + " :  服务端系统繁忙，请稍后再试！";
    }


    public String payment_Global_FallbackHandler(){
        return "客户端: " + Thread.currentThread().getName() + " globle fallback, 服务端系统繁忙，请稍后再试！";
    }
}
