package com.example.lenovo.firevideo.bean;


import android.graphics.Bitmap;
import android.widget.Button;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

//视频类
public class Video extends BmobObject {
    private String VideoId;//视频ID
    private String VideoFace;//上传视频封面
    private String VideoUrl;//视频的地址
    public  Integer Video_Total_like;//视频总点赞数
    public  Integer Video_Comment_Num;//评论量
    public  Integer Video_Energy_Value;//视频能量值
    public String VideoTag;//视频标签
    public Video(){

    }

    public void setVideoUrl(String videoUrl) {
        this.VideoUrl = videoUrl;
    }
    public void setVideoId(String videoId){
        this.VideoId=videoId;
    }

    public void setVideoFace(String videoFace){
        this.VideoFace=videoFace;
    }

    public   void setVideo_Total_like(Integer video_Total_like) {
        Video_Total_like = video_Total_like;
    }

    public   void setVideo_Comment_Num(Integer video_Comment_Num) {
        Video_Comment_Num = video_Comment_Num;
    }

    public   void setVideo_Energy_Value(Integer video_Energy_Value) {
        Video_Energy_Value = video_Energy_Value;
    }

    public void setVideoTag(String videoTag) {
        VideoTag = videoTag;
    }
    public String getVideoId(){
        return  VideoId;
    }

    public String getVideoFace(){return VideoFace;}

    public   Integer getVideo_Comment_Num() {
        return Video_Comment_Num;
    }

    public   Integer getVideo_Energy_Value() {
        return Video_Energy_Value;
    }

    public   Integer getVideo_Total_like() {
        return Video_Total_like;
    }

    public String getVideoTag() {
        return VideoTag;
    }

    public String getVideoUrl() {
        return VideoUrl;
    }
}
