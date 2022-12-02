package com.example.webspring.controller;

import com.example.webspring.entity.User;
import com.example.webspring.mapper.IndexMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin //允许跨域请求数据
public class IndexController {
    public static User user;

    @Autowired //注入
    private IndexMapper indexMapper;
    //注册
    @ApiOperation("传进姓名、密码,若用户名已注册过返回existed,否则返回id+name")
    @GetMapping("/register")
    @CrossOrigin
    public int register(User user){
        if(indexMapper.isNameExisted(user.getName())){
            return -1;
        }
        int id=indexMapper.selectCount(null);
        user.setId(id);
        indexMapper.insert(user);
        return id;
    }

    @ApiOperation("传进姓名、密码，若成功登录返回用户id,否则返回-1")
    @GetMapping("/login")
    @CrossOrigin
    public int login(String logName, String logPass){
        if(!indexMapper.isNameExisted(logName)){
            return -1;
        }
        if(indexMapper.selectPassFromName(logName).equals(logPass)){
            return indexMapper.selectIdFromName(logName);
        }
        return -2;
    }
}
