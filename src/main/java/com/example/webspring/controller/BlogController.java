package com.example.webspring.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.webspring.entity.Blog;
import com.example.webspring.entity.Comment;
import com.example.webspring.entity.Love;
import com.example.webspring.mapper.BlogMapper;
import com.example.webspring.mapper.CommentMapper;
import com.example.webspring.mapper.LoveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class BlogController {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private LoveMapper loveMapper;

    @GetMapping("/allBlog")
    public List<Blog> getBlogs(int n){
        return blogMapper.selectList(new QueryWrapper<Blog>().lt("blogid",n));
    }

    @GetMapping("/myBlog")
    public List<Blog> getMyBlog(int userid,int n){
        List<Blog> myBlogs=blogMapper.selectList(new QueryWrapper<Blog>().eq("userid",userid));
        return myBlogs.subList(0,n);
    }

    @GetMapping("/getOneBlog")
    public Blog getOneBlog(int blogid){
        return blogMapper.selectOne(new QueryWrapper<Blog>().eq("blogid",blogid));
    }

    @GetMapping("/deleteBlog")
    public boolean deleteBlog(int blogid){
        blogMapper.delete(new QueryWrapper<Blog>().eq("blogid",blogid));
        for(int i=blogid+1;i<blogMapper.countBlog()+1;i++){
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

    @GetMapping("/writeComment")
    public boolean writeComment(Comment comment){
        comment.setCommentid(commentMapper.countComment());
        commentMapper.insert(comment);
        return true;
    }

    @GetMapping("/getComment")
    public List<Comment> getBlogComment(int blogid){
        return commentMapper.selectList(new QueryWrapper<Comment>().eq("blogid",blogid));
    }

    @GetMapping("/likeNum")
    public int loveNum(int blogid){
        return loveMapper.selectCount(new QueryWrapper<Love>().eq("blogid",blogid));
    }

    @GetMapping("/haveLiked")
    public boolean haveLiked(int blogid,int userid){
        QueryWrapper<Love> wrapper=new QueryWrapper<Love>();
        wrapper.eq("blogid",blogid);
        wrapper.eq("userid",userid);
        return loveMapper.selectCount(wrapper)!=0;
    }

    @GetMapping("/like")
    public boolean like(Love love){
        love.setLoveid(loveMapper.selectCount(null));
        loveMapper.insert(love);
        return true;
    }

}
