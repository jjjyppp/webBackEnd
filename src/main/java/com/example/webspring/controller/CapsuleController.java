package com.example.webspring.controller;

import com.example.webspring.entity.Capsule;
import com.example.webspring.mapper.CapsuleMapper;
import io.swagger.annotations.ApiOperation;
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
public class CapsuleController {
    @Autowired //注入
    private CapsuleMapper capsuleMapper;

    @ApiOperation("传入userid,title,content,opentime，返回这个胶囊的ID")
    @GetMapping("/makeCapsule")
    @CrossOrigin
    public int makeCapsule(Capsule capsule){
        Random random=new Random();
        random.setSeed(10000L);

        int maxID = 1000;
        int id=random.nextInt(maxID);
        while(capsuleMapper.isCapsuleExisted(id)) {
            id = random.nextInt(maxID);
        }
        capsule.setCapsuleid(id);

        Timestamp time=new Timestamp(System.currentTimeMillis());
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStr = df.format(time);
        time=Timestamp.valueOf(timeStr);
        capsule.setWritetime(time);

        capsule.setIsopened(false);
        capsuleMapper.insert(capsule);

        return id;
    }

    @ApiOperation("返回我的所有时间胶囊")
    @GetMapping("/getMyCapsule")
    @CrossOrigin
    public List<Capsule> getMyCapsule(int userid){
        return capsuleMapper.selectMyCapsule(userid);
    }

    @ApiOperation("判断是否能打开，如果能，就返回这个时间胶囊，如果不能，就返回null")
    @GetMapping("/openCapsule")
    @CrossOrigin
    public Capsule openCapsule(int capsuleid){
        Capsule capsule=capsuleMapper.selectCapsule(capsuleid).get(0);

        Timestamp nowTime=new Timestamp(System.currentTimeMillis());
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStr = df.format(nowTime);
        nowTime=Timestamp.valueOf(timeStr);

        if(nowTime.after(capsule.getOpentime())){
            capsule.setIsopened(true);
            capsuleMapper.openedUpdate(capsuleid);
            return capsule;
        }
        else return null;

    }
}
