package com.halin.springcloud.controller;

import com.halin.springcloud.entities.CommonResult;
import com.halin.springcloud.entities.Payment;
import com.halin.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @RequestMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id){
        return paymentFeignService.getPaymentById(id);
    }

    //给hystrix做超时调用测试
    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout(){
      //openfeign-ribbon，客户端一般默认调用超时是1秒钟
      return paymentFeignService.paymentFeignTimeout();
    }

}
