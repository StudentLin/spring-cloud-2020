package com.halin.springboot.controller;

import com.halin.springboot.service.impl.MessageProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMessageController {
    @Resource
    private MessageProvider messageProvider;

    @RequestMapping("/send")
    public String sendMessage(){
        return this.messageProvider.send();
    }

}
