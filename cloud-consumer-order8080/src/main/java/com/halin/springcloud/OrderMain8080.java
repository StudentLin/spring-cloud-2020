package com.halin.springcloud;

import com.halin.myrule.MyRibbonRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
// 启动自定义ribbon轮询算法
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = MyRibbonRule.class)
public class OrderMain8080 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain8080.class, args);
    }
}
