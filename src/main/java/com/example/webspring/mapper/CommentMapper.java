package com.example.webspring.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.webspring.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
