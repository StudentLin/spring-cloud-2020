package com.halin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        return "------------ test A -------------";
    }


    @GetMapping("/testB")
    public String testB(){
        return "------------ test B -------------";

    }


}
