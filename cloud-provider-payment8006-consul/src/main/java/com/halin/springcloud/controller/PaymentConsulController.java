package com.halin.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/provider")
public class PaymentConsulController {
    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/payment/consul")
    public String paymentZk() {
        return "spring cloud with zookeeper: " + serverPort + "\r\n " + UUID.randomUUID().toString();
    }
}
