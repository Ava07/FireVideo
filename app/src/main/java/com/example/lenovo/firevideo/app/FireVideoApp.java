package com.example.lenovo.firevideo.app;

import android.app.Application;

import com.example.lenovo.firevideo.utils.Constans;
import com.example.lenovo.firevideo.utils.NameUtils;
import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;

import org.anyrtc.rtmpc_hybrid.RTMPCHybrid;

//全局的生命周期管理者，app打卡一启动就会最先执行这个类里面的方法，实现APP的进程管理
public class FireVideoApp extends Application {
    private static FireVideoApp sInstance;
    private static String NickName="";
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        NickName= NameUtils.getNickName();
        // 初始化RTMPC引擎 并配置开发者信息
        RTMPCHybrid.Inst().initEngineWithAnyrtcInfo(getApplicationContext(), Constans.DEVELOPERID, Constans.APPID, Constans.APPKEY, Constans.APPTOKEN);

        //配置私有云
//        RTMPCHybrid.Inst().configServerForPriCloud("", 0);
        RTMPCHybrid.disableHWDecode();
        InitializationConfig config = InitializationConfig.newBuilder(this)
                .connectionTimeout(15*1000)
                .readTimeout(15*1000)
                .retry(1).build();
        NoHttp.initialize(config);
        Logger.setDebug(true); // 开启NoHttp调试模式。
        Logger.setTag("HttpInfo"); // 设置NoHttp打印Log的TAG。
    }
    public  static Application App(){
        return sInstance;
    }
    public static FireVideoApp getInstance() {
        return sInstance;
    }

    public static String getAnyRTCId(){
        return (int)((Math.random()*9+1)*100000)+"";
    }
    public static String getNickName(){
        return NickName;
    }

}
