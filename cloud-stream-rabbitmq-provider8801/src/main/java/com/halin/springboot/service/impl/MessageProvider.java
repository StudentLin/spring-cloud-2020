package com.halin.springboot.service.impl;

import cn.hutool.core.lang.UUID;
import com.halin.springboot.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@EnableBinding(Source.class) // 定义消息的推送管道（该类是个消息发送者）
@Component
public class MessageProvider implements IMessageProvider {
    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String uuid = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(uuid).build());
        System.out.println(String.format("**********service UUID:%s 已经发送", uuid));
        return uuid;
    }
}
