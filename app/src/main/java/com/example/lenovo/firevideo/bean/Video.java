package com.example.lenovo.firevideo.bean;

import android.widget.ImageView;

import cn.bmob.v3.BmobObject;

//视频类
public class Video extends BmobObject {
    private String VideoId;//视频ID
    private String VideoFace;//上传视频封面
    public static Integer Video_Total_like;//视频总点赞数
    public static Integer Video_Comment_Num;//评论量
    public static Integer Video_Energy_Value;//视频能量值
    public String VideoTag;//视频标签
    public Video(){

    }
    public void setVideoId(String videoId){
        this.VideoId=videoId;
    }

    public void setVideoFace(String videoFace) {
        VideoFace = videoFace;
    }

    public static void setVideo_Total_like(Integer video_Total_like) {
        Video_Total_like = video_Total_like;
    }

    public static void setVideo_Comment_Num(Integer video_Comment_Num) {
        Video_Comment_Num = video_Comment_Num;
    }

    public static void setVideo_Energy_Value(Integer video_Energy_Value) {
        Video_Energy_Value = video_Energy_Value;
    }

    public void setVideoTag(String videoTag) {
        VideoTag = videoTag;
    }
    public String getVideoId(){
        return  VideoId;
    }

    public String getVideoFace() {
        return VideoFace;
    }

    public static Integer getVideo_Comment_Num() {
        return Video_Comment_Num;
    }

    public static Integer getVideo_Energy_Value() {
        return Video_Energy_Value;
    }

    public static Integer getVideo_Total_like() {
        return Video_Total_like;
    }

    public String getVideoTag() {
        return VideoTag;
    }
}
