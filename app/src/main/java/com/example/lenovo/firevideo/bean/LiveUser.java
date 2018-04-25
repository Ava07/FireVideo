package com.example.lenovo.firevideo.bean;

import cn.bmob.v3.BmobObject;

//直播用户类
public class LiveUser extends BmobObject {
    private String LiveId;//直播房间号
    private String UserId;//实时直播用户的ID
    public LiveUser(){

    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public void setLiveId(String liveId) {
        LiveId = liveId;
    }

    public String getUserId() {
        return UserId;
    }

    public String getLiveId() {
        return LiveId;
    }
}
