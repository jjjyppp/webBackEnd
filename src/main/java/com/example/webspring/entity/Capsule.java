package com.example.webspring.entity;

import java.sql.Timestamp;

public class Capsule {
    private int capsuleid;
    private int userid;
    private String title;
    private String content;
    private Timestamp writetime;
    private Timestamp opentime;
    private boolean isopened;

    public int getCapsuleid() {
        return capsuleid;
    }

    public void setCapsuleid(int capsuleid) {
        this.capsuleid = capsuleid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getWritetime() {
        return writetime;
    }

    public void setWritetime(Timestamp writetime) {
        this.writetime = writetime;
    }

    public Timestamp getOpentime() {
        return opentime;
    }

    public void setOpentime(Timestamp opentime) {
        this.opentime = opentime;
    }

    public boolean isIsopened() {
        return isopened;
    }

    public void setIsopened(boolean isopened) {
        this.isopened = isopened;
    }
}
