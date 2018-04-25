package com.example.lenovo.firevideo.bean;

import cn.bmob.v3.BmobObject;

//评论视频类
public class CommentVideo extends BmobObject {
    private String VideoId;//被评论视频ID
    private String CommentId;//评论ID
    public CommentVideo(){

    }

    public void setVideoId(String videoId) {
        VideoId = videoId;
    }

    public void setCommentId(String commentId) {
        CommentId = commentId;
    }

    public String getVideoId() {
        return VideoId;
    }

    public String getCommentId() {
        return CommentId;
    }
}
