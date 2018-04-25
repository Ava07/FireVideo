package com.example.lenovo.firevideo.app;

import android.app.Application;

//全局的生命周期管理者，app打卡一启动就会最先执行这个类里面的方法，实现APP的进程管理
public class FireVideoApp extends Application {
    private static FireVideoApp sInstance;
    public static FireVideoApp getInstance() {
        return sInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

}
