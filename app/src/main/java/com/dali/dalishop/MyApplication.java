package com.dali.dalishop;

import android.app.Application;

import com.zhy.autolayout.config.AutoLayoutConifg;

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        AutoLayoutConifg.getInstance().useDeviceSize();
    }
}
