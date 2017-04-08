package com.zykj.yixiu.app;

import android.app.Application;

import com.zykj.yixiu.utils.Y;

/**
 * Created by zykj on 2017/4/8.
 */

public class MyApp extends Application {
    //初始化
    @Override
    public void onCreate() {
        super.onCreate();
        Y.context = this;
    }
}
