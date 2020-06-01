package com.halin.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id){
        return "线程池: " + Thread.currentThread().getName() + " paymentInfo_OK " + id;
    }

    //=========================== 服务降级 ==============================================

    /**
     * 模拟程序超时，测试hystrix进行服务降级
     * 如果3秒内能响应请求，就正常相应，如果不行就执行fallback->paymentInfo_TimeOutHandler
     *
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "9000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        try {
            // hystrix能处理系统报错
            //int i = 10/0;
            //hystrix也能处理系统超时
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "线程池: " + Thread.currentThread().getName() + " paymentInfo_TimeOut " + id;
    }


    public String paymentInfo_TimeOutHandler(Integer id){
        return "我是服务端8001: " + Thread.currentThread().getName() + " paymentInfo_TimeOutHandler " + id + " :  系统繁忙，请稍后再试！";
    }


    //=========================== 服务熔断 ==============================================



    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            // 在10000ms内 十次请求中有60%以上失败，发生短路
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //失败率达到多少后跳闸

    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0){
            throw new RuntimeException("********* id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "fallback, id不能为负数！！！！！！！！！！！";
    }


}
