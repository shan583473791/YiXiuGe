package com.zykj.yixiu.utils;

import android.content.Context;
import android.widget.Toast;

import java.util.logging.Logger;

/**
 * Created by zykj on 2017/4/8.
 */

public class Y {
    //工具类
    public static Context context;

    public static void i(String src){
        //log  i
        com.orhanobut.logger.Logger.i(src);
    }

    public static void t(String src){
        Toast.makeText(context, src, Toast.LENGTH_SHORT).show();
    }
}
