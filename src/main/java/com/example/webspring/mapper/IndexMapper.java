package com.example.webspring.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.webspring.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IndexMapper extends BaseMapper<User> {
    @Select("select password from user where name=#{name}")
    public String selectPassFromName(String name);

    @Select("select id from user where name=#{name}")
    public int selectIdFromName(String name);

    @Select("select count(*) from user where name=#{name}")
    public boolean isNameExisted(String name);
}