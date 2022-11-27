package com.example.webspring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.webspring.mapper")
public class WebSpringApplication{
    public static void main(String[] args) {
        SpringApplication.run(WebSpringApplication.class, args);
    }

}
