package com.zykj.yixiu.utils;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.hss01248.dialog.StyledDialog;

import org.xutils.common.Callback;
import org.xutils.common.util.KeyValue;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * //工具类
 * Created by zykj on 2017/4/8.
 */

public class Y {

    public static Context context; //全局上下文

    public static boolean isLog = true; //控制日志打印的开关

    public static User user;
    public static String TOKEN;  //用户类

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Y.user = user;
    }

    /**
     * 吐司功能只需要传入一个 字符串
     *
     * @param str
     */
    public static void t(String str) {
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }

    /**
     * 输出log日志
     *
     * @param str
     */
    public static void i(String str) {
        if (isLog)
            com.orhanobut.logger.Logger.i(str);
    }

    /**
     * 检测请求返回的数据是否正确
     */
    public static boolean getRespCode(String result) {
        if ("0".equals(JSON.parseObject(result).getString("resp_code"))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 如果成功获取数据
     */
    public static String getData(String result) {
        return JSON.parseObject(result).getString("data");
    }

    /**
     * get请求  返回成功回调
     *
     * @param params
     * @param call
     * @return
     */
    public static Callback.Cancelable get(String url, Map<String, String> params, MyCommonCall<String> call) {
        //请求的对象
        RequestParams rp = new RequestParams(url);
        if (params == null)
            i(url);
        //检测外部是否传入了参数
        if (params != null) {
            //把参数取出来这是到rp
            i(rp.toString());
            for (Map.Entry<String, String> entry : params.entrySet()) {
                rp.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }
        // 只要发起Get请求就开启对话框
        StyledDialog.buildLoading().show();
        return x.http().get(rp, call);
    }

    /**
     * post请求  返回成功回调
     *
     * @param params
     * @param call
     * @return
     */
    public static void postFile(final RequestParams params, final MyCommonCall<String> call) {
        StyledDialog.buildLoading().show();
        List<KeyValue> fileParams = params.getFileParams();
        for (final KeyValue k : fileParams) {
            File flie = (File) k.value;
            i("压缩前的大小" + flie.length());
            Luban.get(context).load(flie).putGear(Luban.THIRD_GEAR).setCompressListener(new OnCompressListener() {
                @Override
                public void onStart() {

                }

                @Override
                public void onSuccess(File file) {
                    i("压缩后的大小" + file.length());
                    params.addBodyParameter(k.key, file);
                    params.setMultipart(true);
                    i(params.toString());
                    x.http().post(params, call);
                }

                @Override
                public void onError(Throwable e) {

                }
            }).launch();

        }


        x.http().post(params, call);
    }

    /**
     * post请求  返回成功回调
     *
     * @param params
     * @param call
     * @return
     */
    public static Callback.Cancelable post(RequestParams params, MyCommonCall<String> call) {
        StyledDialog.buildLoading().show();

        return x.http().post(params, call);
    }

    /**
     * 实现不需要外部完成的两个函数
     */
    public abstract static class MyCommonCall<String> implements Callback.CommonCallback<String> {
        @Override
        public void onFinished() {
        }

        @Override
        public void onCancelled(CancelledException cex) {
        }

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
            StyledDialog.dismissLoading();
            t("服务器异常");
            ex.printStackTrace();
        }
    }


}
