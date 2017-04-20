package com.zykj.yixiu.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.hss01248.dialog.MyActyManager;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.utils.Y;

import org.xutils.x;

/**
 * Created by zykj on 2017/4/8.
 */

public class MyApp extends Application {
    //初始化

    @Override
    public void onCreate() {
        super.onCreate();
        StyledDialog.init(this);
        Y.context = this;
        x.Ext.init(this);
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                //在这里保存顶层activity的引用(内部以软引用实现)
                MyActyManager.getInstance().setCurrentActivity(activity);

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

    }

}
