package com.example.lenovo.firevideo.bean;

import cn.bmob.v3.BmobObject;

//点赞视频用户类
public class LikeVideoUser extends BmobObject {
    private String VideoId;//被点赞视频ID
    private String UserId;//发起点赞的用户ID
    public LikeVideoUser(){

    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public void setVideoId(String videoId) {
        VideoId = videoId;
    }

    public String getUserId() {
        return UserId;
    }

    public String getVideoId() {
        return VideoId;
    }
}
