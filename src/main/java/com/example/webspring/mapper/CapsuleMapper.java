package com.example.webspring.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.webspring.entity.Capsule;
import com.example.webspring.entity.Drifter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

@Mapper
public interface CapsuleMapper extends BaseMapper<Capsule> {
    @Select("select count(*) from capsule where capsuleid=#{capsuleid}")
    public boolean isCapsuleExisted(int capsuleid);

    @Select("select * from capsule where userid=#{userid}")
    public List<Capsule> selectMyCapsule(int userid);

    @Select("select * from capsule where capsuleid=#{capsuleid}")
    public List<Capsule> selectCapsule(int capsuleid);

    @Select("select opentime from capsule where capsuleid=#{capsuleid}")
    public Date getOpentime(int capsuleid);

    @Update("update capsule SET isopened=1 WHERE capsuleid=#{capsuleid}")
    public void openedUpdate(int capsuleid);
}
