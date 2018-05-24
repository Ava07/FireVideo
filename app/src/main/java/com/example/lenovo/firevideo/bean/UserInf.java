package com.example.lenovo.firevideo.bean;

import android.widget.ImageView;

import java.util.Date;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
//用户类
public class UserInf extends BmobObject {
    private String UserId;//用户ID
    private String Username;//用户名
    private String UserSex;//用户性别
    private String Birthday;//用户生日
    private Integer Follower_Num;//用户粉丝总数
    private Integer Follow_Num;//用户关注总数
    private String UserCity;//用户所处城市
    private String UserHead;//用户头像
    private String PhoneNum;//就是用户手机号
    private  String UserKey;//登陆密码
    private  String UnderWrite;//个性签名
    public  Integer Total_Upload_Video;//用户上传短视频总数
    public  Integer Total_Upload_Live;//用户直播总数
    public Integer Total_Like;//用户总点赞量
    public  Integer Total_Energy_Value;//用户总能量值
    public UserInf(){

    }
    public UserInf(String Username,String UserHead){
        this.Username = Username;
        this.UserHead = UserHead;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public void setFollow_Num(Integer follow_Num) {
        Follow_Num = follow_Num;
    }

    public void setFollower_Num(Integer follower_Num) {
        Follower_Num = follower_Num;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public void setUserCity(String userCity) {
        UserCity = userCity;
    }

    public void setUnderWrite(String underWrite) {
        UnderWrite = underWrite;
    }

    public void setUserHead(String userHead) {
        UserHead = userHead;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public  void setTotal_Upload_Video(Integer total_Upload_Video) {
        Total_Upload_Video = total_Upload_Video;
    }

    public  void setUserKey(String userKey) {
        UserKey = userKey;
    }


    public void setUsername(String username) {
        Username = username;
    }

    public void setUserSex(String userSex) {
        UserSex = userSex;
    }

    public  void setTotal_Energy_Value(Integer total_Energy_Value) {
        Total_Energy_Value = total_Energy_Value;
    }

    public void setTotal_Like(Integer total_Like) {
        Total_Like = total_Like;
    }

    public  void setTotal_Upload_Live(Integer total_Upload_Live) {
        Total_Upload_Live = total_Upload_Live;
    }

    public String getUserId() {
        return UserId;
    }

    public Integer getFollow_Num() {
        return Follow_Num;
    }

    public Integer getFollower_Num() {
        return Follower_Num;
    }

    public String getBirthday() {
        return Birthday;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public Integer getTotal_Upload_Video() {
        return Total_Upload_Video;
    }

    public String getUserCity() {
        return UserCity;
    }

    public String getUserHead() {
        return UserHead;
    }

    public String getUsername() {
        return Username;
    }

    public  Integer getTotal_Like() {
        return Total_Like;
    }

    public String getUserKey() {
        return UserKey;
    }

    public  String getUnderWrite() {
        return UnderWrite;
    }

    public String getUserSex() {
        return UserSex;
    }

    public  Integer getTotal_Upload_Live() {
        return Total_Upload_Live;
    }

    public  Integer getTotal_Energy_Value() {
        return Total_Energy_Value;
    }

}
