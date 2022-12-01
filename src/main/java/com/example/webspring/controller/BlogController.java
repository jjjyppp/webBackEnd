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

    @GetMapping("/deleteBlog")
    public boolean deleteBlog(int blogID){
        blogMapper.delete(new QueryWrapper<Blog>().eq("blogid",blogID));
        for(int i=blogID+1;i<blogMapper.countBlog()+1;i++){
            Blog new_blog=blogMapper.selectOne(new QueryWrapper<Blog>().eq("blogid",i));
            new_blog.setBlogid(i-1);
            blogMapper.update(new_blog,new QueryWrapper<Blog>().eq("blogid",i));
        }
        return true;
    }

    @GetMapping("/writeBlog")
    public int writeBlog(Blog blog){
        blog.setBlogid(blogMapper.countBlog());
        blogMapper.insert(blog);
        return blog.getBlogid();
    }

}
