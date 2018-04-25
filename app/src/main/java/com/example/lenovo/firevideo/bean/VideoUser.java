package com.example.lenovo.firevideo.bean;



import cn.bmob.v3.BmobObject;


//视频用户类
public class VideoUser extends BmobObject {
    private String VideoId;//视频ID
    private String UserId;//用户ID

    public void VideoUser(String videoId,String userId){
        this.VideoId=videoId;
        this.UserId=userId;
    }
    public void setVideoId(String videoId){
        this.VideoId=videoId;
    }
    public void setUserId(String userId){
        this.UserId=userId;
    }

    public String getUserId() {
        return UserId;
    }

    public String getVideoId() {
        return VideoId;
    }
}
