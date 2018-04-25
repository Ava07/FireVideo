package com.example.lenovo.firevideo.bean;

import cn.bmob.v3.BmobObject;

//用户关注类
public class FollowUser extends BmobObject {
    private String FollowId;//主动发起关注的用户ID
    private String FollowedId;//被关注的用户ID
    public FollowUser(){

    }

    public void setFollowedId(String followedId) {
        FollowedId = followedId;
    }

    public void setFollowId(String followId) {
        FollowId = followId;
    }

    public String getFollowedId() {
        return FollowedId;
    }

    public String getFollowId() {
        return FollowId;
    }
}
