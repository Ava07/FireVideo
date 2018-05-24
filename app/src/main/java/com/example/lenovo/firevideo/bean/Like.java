package com.example.lenovo.firevideo.bean;

public class Like {
    private String LikeUserHead;//点赞用户头像路径
    private String LikeUsername;//点赞用户名
    private String VideoFace;//视频封面路径
    public Like(String LikeUserHead,String LikeUsername,String VideoFace){
        this.LikeUserHead = LikeUserHead;
        this.LikeUsername = LikeUsername;
        this.VideoFace = VideoFace;

    }

    public String getLikeUserHead() {
        return LikeUserHead;
    }

    public String getVideoFace() {
        return VideoFace;
    }

    public String getLikeUsername() {
        return LikeUsername;
    }

    public void setVideoFace(String videoFace) {
        VideoFace = videoFace;
    }

    public void setLikeUserHead(String likeUserHead) {
        LikeUserHead = likeUserHead;
    }

    public void setLikeUsername(String likeUsername) {
        LikeUsername = likeUsername;
    }
}
