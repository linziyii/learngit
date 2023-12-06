package com.example.mysql.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {
    @RequestMapping("/hello/get")
    public String hello(){
        return "hello world";
    }
    @PostMapping("/hello/post")
    public String posthello(String map){
        return map;
    }
}
