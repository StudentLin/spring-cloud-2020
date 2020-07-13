package com.halin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentProviderNacosMain9002 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentProviderNacosMain9002.class, args);
    }
}
