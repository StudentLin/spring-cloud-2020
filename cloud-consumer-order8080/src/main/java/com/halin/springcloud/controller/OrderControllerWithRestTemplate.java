package com.halin.springcloud.controller;

import com.halin.springcloud.entities.CommonResult;
import com.halin.springcloud.entities.Payment;
import com.halin.springcloud.lb.MyLoadBalanceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

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


    //以下注入为了使用自定义的负载均衡策略
    @Resource
    private MyLoadBalanceImpl myLoadBalance;
    @Resource
    private DiscoveryClient discoveryClient;


    @GetMapping("/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);

    }

    @GetMapping("/payment/get/entity/{id}")
    public CommonResult getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> re = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/"+ id,
                CommonResult.class);
        if(re.getStatusCode().is2xxSuccessful()){
            return re.getBody();
        }else{
            return new CommonResult(400, "nothing happened");
        }
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("cloud-payment-service");
        if(serviceInstanceList == null || serviceInstanceList.size() == 0 ){
            return null;
        }
        ServiceInstance serviceInstance = myLoadBalance.choseInstance(serviceInstanceList);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb", String.class);

    }

}
