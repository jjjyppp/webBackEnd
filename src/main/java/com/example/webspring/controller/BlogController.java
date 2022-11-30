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

    @GetMapping("/myBlog")
    public List<Blog> getMyBlog(int userID,int n){
        List<Blog> myBlogs=blogMapper.selectList(new QueryWrapper<Blog>().eq("userid",userID));
        return myBlogs.subList(0,n);
    }

    @GetMapping("/getOneBlog")
    public Blog getOneBlog(int blogID){
        return blogMapper.selectOne(new QueryWrapper<Blog>().eq("blogid",blogID));
    }
}
