package com.example.webspring.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin //允许跨域请求数据
public class IndexController {
    //注册
    @GetMapping("/register")
    @CrossOrigin
    public String register(String regisName,String regisPass,String regisVerPass){
        return regisName+regisPass+regisVerPass;
    }

    //登陆
    @GetMapping("/login")
    @CrossOrigin
    public String login(String logName,String logPass){
        return logName+logPass;
    }


}
