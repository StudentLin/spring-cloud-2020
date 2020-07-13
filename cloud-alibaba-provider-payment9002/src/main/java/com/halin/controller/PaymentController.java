package com.halin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/payment/nacos/{id}")
    public String getPayment(@PathVariable Integer id){
        return "nacos registry, server port: " + this.serverPort;
    }

}
