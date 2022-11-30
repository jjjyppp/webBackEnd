package com.example.webspring.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.webspring.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface BlogMapper extends BaseMapper<Blog> {
//    @Select("select * from blog where blog_id<#{n}")
//    public List<Blog> selectBlogs(int n);
    @Select("select count(*) from blog")
    public int test();
}
