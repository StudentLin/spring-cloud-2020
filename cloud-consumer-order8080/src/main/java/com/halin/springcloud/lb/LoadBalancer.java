package com.halin.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {

    ServiceInstance choseInstance(List<ServiceInstance> serviceInstanceList);

}
