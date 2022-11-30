package com.example.webspring.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.webspring.entity.Blog;
import com.example.webspring.mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin

public class BlogController {
    @Autowired
    private BlogMapper blogMapper;

    @GetMapping("/allBlog")
    public List<Blog> getBlogs(int n){
        return blogMapper.selectList(new QueryWrapper<Blog>().lt("blogid",n));
    }
}
