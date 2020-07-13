package com.halin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderConsumerMain8083 {
    public static void main(String[] args) {
        SpringApplication.run(OrderConsumerMain8083.class, args);
    }
}

