package com.halin.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLoadBalanceImpl implements LoadBalancer{
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int current;
        int next;
        do{
            current = this.atomicInteger.get();
            next = current>Integer.MAX_VALUE ? 0: current++;
        }while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("==== current: " + current + " next: " + next + "====");
        return current;
    }

    // 自定义模仿负载均衡轮询算法：rest接口第几次请求 % 服务器集群给总数量 = 实际该调用的服务器位置下标，每次服务重启后rest接口重新开始计数
    @Override
    public ServiceInstance choseInstance(List<ServiceInstance> serviceInstanceList) {
        int index = getAndIncrement() % serviceInstanceList.size();
        return serviceInstanceList.get(index);
    }
}
