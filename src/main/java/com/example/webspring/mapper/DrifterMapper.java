package com.example.webspring.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.webspring.entity.Drifter;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DrifterMapper extends BaseMapper<Drifter> {

    @Select("select * from drifter where pickerid=#{pickerid}")
    public List<Drifter> selectMyDrifter(int pickerid);

    @Select("select count(*) from drifter where id=#{id}")
    public boolean isDrifterExisted(int id);

    @Select("select * from drifter where id=#{id}")
    public List<Drifter> selectDrifter(int id);

    @Select("select ispicked from drifter where id=#{id}")
    public boolean isPicked(int id);

    @Update("update drifter SET pickerid=#{pickerid}, ispicked=1 WHERE id=#{id}")
    public void pickedUpdate(int id,int pickerid);

    @Delete("delete from drifter where id=#{id}")
    public void deleteDrifter(int id);
}
