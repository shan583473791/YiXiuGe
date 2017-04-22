package com.zykj.yixiu.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hss01248.dialog.MyActyManager;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.utils.Y;

import org.xutils.x;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.widget.GFImageView;

/**
 * Created by zykj on 2017/4/8.
 */

public class MyApp extends Application {
    private Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        StyledDialog.init(this);

        x.Ext.init(this);
        Y.context=this;
        GalleryFinal.init(coreConfig);
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

    //图片加载器
    ThemeConfig themeConfig = new ThemeConfig.Builder().build();
    FunctionConfig functionConfig = new FunctionConfig.Builder()
            .setEnablePreview(true)//开启预览功能
            .build();

    class myImageLoader implements ImageLoader {
        @Override
        public void displayImage(Activity activity, String path, final GFImageView imageView, Drawable defaultDrawable, int width, int height) {
            //借助glide帮galleryfinal加载图片
            Glide.with(activity)
                    .load("file://" + path)                        //文件地址
                    .placeholder(defaultDrawable)                //加载中图片
                    .error(defaultDrawable)                        //错误图片
                    .override(width, height)                        //图片宽高
                    .diskCacheStrategy(DiskCacheStrategy.NONE)    //禁止磁盘缓存
                    .skipMemoryCache(true)                        //禁止内存缓存
                    .into(imageView);                            //加载图片到GalleryFinal
        }

        @Override
        public void clearMemoryCache() {                //清除缓存不需要搭理
        }
    }
    CoreConfig coreConfig = new CoreConfig.Builder(this, new myImageLoader(), themeConfig)
            .setFunctionConfig(functionConfig)
            .build();


}
