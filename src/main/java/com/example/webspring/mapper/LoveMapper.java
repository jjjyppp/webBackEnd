package com.example.webspring.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.webspring.entity.Love;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoveMapper extends BaseMapper<Love> {

}
