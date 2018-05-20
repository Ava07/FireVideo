package com.example.lenovo.firevideo.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

//直播类
public class Live extends BmobObject {
    private String LiveId;//直播房间号
    private String LiveFace;//直播封面
    private Integer Line_Watch_Num;//在线观看人数
    private Integer Live_Energy_Value;//总能量值
    private Integer Live_Total_Time;
    public Live(){

    }

    public void setLiveId(String liveId) {
        LiveId = liveId;
    }

    public void setLine_Watch_Num(Integer line_Watch_Num) {
        Line_Watch_Num = line_Watch_Num;
    }

    public void setLive_Energy_Value(Integer live_Energy_Value) {
        Live_Energy_Value = live_Energy_Value;
    }

    public void setLive_Total_Time(Integer live_Total_Time) {
        Live_Total_Time = live_Total_Time;
    }

    public void setLiveFace(String liveFace) {
        LiveFace = liveFace;
    }

    public Integer getLive_Energy_Value() {
        return Live_Energy_Value;
    }

    public String getLiveId() {
        return LiveId;
    }

    public Integer getLine_Watch_Num() {
        return Line_Watch_Num;
    }

    public Integer getLive_Total_Time() {
        return Live_Total_Time;
    }

    public String getLiveFace() {
        return LiveFace;
    }

}
