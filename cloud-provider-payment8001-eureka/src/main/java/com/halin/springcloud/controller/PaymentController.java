package com.halin.springcloud.controller;

import com.halin.springcloud.entities.CommonResult;
import com.halin.springcloud.entities.Payment;
import com.halin.springcloud.service.PaymentService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.print.attribute.HashAttributeSet;
import java.util.*;

@RestController
@Slf4j
@Log4j
@RequestMapping("/provider")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = this.paymentService.create(payment);
        log.info(String.format(" **** create result : %s **** ", result));
        if(result > 0){
            return new CommonResult(200, "success: port: " + this.serverPort , result);
        }else{
            return new CommonResult(444, "fail: port: " + this.serverPort);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = this.paymentService.getPaymentById(id);
        if(payment != null){
            log.info(String.format(" **** get result : %s **** ", payment.toString()));
            return new CommonResult(200, "success: port: " + this.serverPort, payment);
        }else{
            return new CommonResult(444, "fail: port: " + this.serverPort);
        }
    }

    @GetMapping("/payment/service/discovery")
    public CommonResult serviceDiscovery(){
        List<String> services =  this.discoveryClient.getServices();
        Map serviceMap = new HashMap<String, Object>();
        serviceMap.put("service", services);
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVER");
        List<String> instanceDetails = new ArrayList<>();
        for (ServiceInstance instance : instances) {
            instanceDetails.add(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        serviceMap.put("server-instance", instanceDetails);
        return new CommonResult(200, "success: port: " + this.serverPort, serviceMap);
    }



}
