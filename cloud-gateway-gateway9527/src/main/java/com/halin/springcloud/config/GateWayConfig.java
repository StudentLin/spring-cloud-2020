package com.halin.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        // 模拟访问国际版百度新闻网站 getaway http://news.baidu.com
        return builder.routes()
                .route("path_route", r -> r.path("/guonei")
                        .uri("http://news.baidu.com/guonei"))
                .build();
    }
}
