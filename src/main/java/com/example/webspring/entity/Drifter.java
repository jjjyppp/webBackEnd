package com.example.webspring.entity;

import java.sql.Timestamp;

public class Drifter {
    private int id;
    private Timestamp time;
    private String title;
    private String content;
    private int pickerid;
    private boolean ispicked;

    Drifter(){}

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent(){
        return content;
    }

    public void setPickerid(int pickerid) {
        this.pickerid = pickerid;
    }

    public int getPickerid() {
        return pickerid;
    }

    public void setTime(Timestamp timestamp){
        this.time=timestamp;
    }

    public Timestamp getTime(){
        return time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setIspicked(boolean ispicked){
        this.ispicked=ispicked;
    }

    public boolean isIspicked() {
        return ispicked;
    }
}
