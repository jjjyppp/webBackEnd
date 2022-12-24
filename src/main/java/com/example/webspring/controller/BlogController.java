package com.example.webspring.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.webspring.entity.*;
import com.example.webspring.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private IndexMapper indexMapper;

    @GetMapping("/allBlog")
    public List<Blog> getBlogs(int n){
        return blogMapper.selectList(new QueryWrapper<Blog>().lt("blogid",n));
    }

    @GetMapping("/myBlog")
    public List<Blog> getMyBlog(int userid,int n){
        List<Blog> myBlogs=blogMapper.selectList(new QueryWrapper<Blog>().eq("userid",userid));
        return myBlogs.size()>n?myBlogs.subList(0,n):myBlogs;
    }

    @GetMapping("/getOneBlog")
    public Blog getOneBlog(int blogid){
        return blogMapper.selectOne(new QueryWrapper<Blog>().eq("blogid",blogid));
    }

    @GetMapping("/deleteBlog")
    public boolean deleteBlog(int blogid){
        blogMapper.delete(new QueryWrapper<Blog>().eq("blogid",blogid));
        for(int i=blogid+1;i<blogMapper.selectCount(null)+1;i++){
            Blog new_blog=blogMapper.selectOne(new QueryWrapper<Blog>().eq("blogid",i));
            new_blog.setBlogid(i-1);
            blogMapper.update(new_blog,new QueryWrapper<Blog>().eq("blogid",i));
        }
        return true;
    }

    @GetMapping("/writeBlog")
    public int writeBlog(Blog blog){
        blog.setBlogid(blogMapper.selectCount(null));
        blog.setUsername(indexMapper.selectOne(new QueryWrapper<User>().
                eq("id",blog.getUserid())).getName());
        blogMapper.insert(blog);
        return blog.getBlogid();
    }

    @GetMapping("/writeComment")
    public boolean writeComment(Comment comment){
        comment.setCommentid(commentMapper.selectCount(null));
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

    @GetMapping("/dislike")
    public boolean dislike(int blogid,int userid){
        QueryWrapper<Love> wrapper=new QueryWrapper<Love>();
        wrapper.eq("blogid",blogid);
        wrapper.eq("userid",userid);
        Love l=loveMapper.selectOne(wrapper);
        int l_id=l.getLoveid();
        loveMapper.delete(new QueryWrapper<Love>().eq("loveid",l_id));
        for(int i=l_id+1;i<loveMapper.selectCount(null)+1;i++){
            Love tmp=loveMapper.selectOne(new QueryWrapper<Love>().eq("loveid",i));
            tmp.setLoveid(i-1);
            loveMapper.update(tmp,new QueryWrapper<Love>().eq("loveid",i));
        }
        return true;
    }

    @GetMapping("/cancelCollect")
    public boolean cancelCollect(int blogid,int userid){
        QueryWrapper<Collect> wrapper=new QueryWrapper<Collect>();
        wrapper.eq("blogid",blogid);
        wrapper.eq("userid",userid);
        Collect c=collectMapper.selectOne(wrapper);
        int c_id=c.getCollectid();
        collectMapper.delete(new QueryWrapper<Collect>().eq("collectid",c_id));
        for(int i=c_id+1;i<collectMapper.selectCount(null)+1;i++){
            Collect tmp=collectMapper.selectOne(new QueryWrapper<Collect>().eq("collectid",i));
            tmp.setCollectid(i-1);
            collectMapper.update(tmp,new QueryWrapper<Collect>().eq("collectid",i));
        }
        return true;
    }
    @GetMapping("/collect")
    public boolean collect(Collect collect){
        collect.setCollectid(collectMapper.selectCount(null));
        collectMapper.insert(collect);
        return true;
    }

    @GetMapping("/allCollect")
    public List<Blog> allCollect(int userid){
        List<Collect> collects= collectMapper.selectList(new QueryWrapper<Collect>().eq("userid", userid));
        List<Blog> blogs=new ArrayList<>();
        for(Collect c:collects){
            blogs.add(blogMapper.selectOne(new QueryWrapper<Blog>().eq("blogid",c.getBlogid())));
        }
        return blogs;
    }

    @GetMapping("/getBlogNumber")
    public int getBlogNumber(int userid){
        return blogMapper.selectCount(new QueryWrapper<Blog>().eq("userid",userid));
    }

    @GetMapping("/getLikes")
    public int getLikes(int userid){
        int res=0;
        List<Love> loves=loveMapper.selectList(null);
        for(Love l:loves){
            Blog b=blogMapper.selectOne(new QueryWrapper<Blog>().eq("blogid",l.getBlogid()));
            if(b.getUserid()==userid){
                res++;
            }
        }
        return res;
    }

    @GetMapping("/getCollect")
    public int getCollect(int userid){
        int res=0;
        List<Collect> collects=collectMapper.selectList(null);
        for(Collect c:collects){
            Blog b=blogMapper.selectOne(new QueryWrapper<Blog>().eq("blogid",c.getBlogid()));
            if(b.getUserid()==userid){
                res++;
            }
        }
        return res;
    }
}
