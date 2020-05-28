package com.halin.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRibbonRule {

    @Bean
    public IRule myRule(){
        //定义为轮询随机算法
        return new RandomRule();
    }
}
