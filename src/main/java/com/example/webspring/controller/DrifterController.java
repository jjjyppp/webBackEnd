package com.example.webspring.controller;

import com.example.webspring.entity.Drifter;
import com.example.webspring.mapper.DrifterMapper;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin //允许跨域请求数据
public class DrifterController {
    @Autowired //注入
    private DrifterMapper drifterMapper;

    @ApiOperation("传入content，timeStamp，返回这个漂流瓶的ID")
    @GetMapping("/writeDrifter")
    @CrossOrigin
    public int writeDrifter(Drifter drifter){
        int id=drifterMapper.selectCount(null);
        drifter.setId(id);

        Timestamp time=new Timestamp(System.currentTimeMillis());
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStr = df.format(time);
        time=Timestamp.valueOf(timeStr);
        drifter.setTime(time);

        drifter.setPickerid(-1);
        drifter.setIspicked(false);

        drifterMapper.insert(drifter);

        return id;
    }

    @ApiOperation("返回我捡到的所有漂流瓶")
    @GetMapping("/getMyDrifter")
    @CrossOrigin
    public List<Drifter> getMyDrifter(int pickerid){
        return drifterMapper.selectMyDrifter(pickerid);
    }

    @ApiOperation("随机捡一个漂流瓶,如果捞了30次还没捞到，返回null")
    @GetMapping("/getDrifter")
    @CrossOrigin
    public Drifter getDrifter(int pickerid){
        int count=0;
        Random random=new Random();
        random.setSeed(10000L);
        int maxID=drifterMapper.selectCount(null);
        int id=random.nextInt(maxID);
        Drifter drifter;
        while(!(drifterMapper.isDrifterExisted(id)&&!drifterMapper.isPicked(id))) {
            if(count>=30) return null;
            id = random.nextInt(maxID);
            count++;
        }
        drifter= drifterMapper.selectDrifter(id).get(0);
        drifter.setPickerid(pickerid);
        drifter.setIspicked(true);
        drifterMapper.pickedUpdate(id,pickerid);

        return drifter;
    }

    @ApiOperation("删除漂流瓶")
    @GetMapping("/deleteDrifter")
    @CrossOrigin
    public Boolean deleteDrifter(int id){
        if(!drifterMapper.isDrifterExisted(id)) return false;
        else drifterMapper.deleteDrifter(id);
        return true;
    }

}
