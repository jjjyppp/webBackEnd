package com.example.webspring.entity;

public class Comment {
    int commentid;
    int userid;
    int blogid;
    String content;

    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getBlogid() {
        return blogid;
    }

    public void setBlogid(int blogid) {
        this.blogid = blogid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentid=" + commentid +
                ", userid=" + userid +
                ", blogid=" + blogid +
                ", content='" + content + '\'' +
                '}';
    }
}
