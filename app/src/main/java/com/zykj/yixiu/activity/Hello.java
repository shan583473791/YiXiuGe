package com.zykj.yixiu.activity;

import android.app.Activity;
import android.os.Bundle;

import com.umeng.analytics.MobclickAgent;
import com.zykj.yixiu.R;

/**
 * Created by zykj on 2017/4/8.
 */

public class Hello extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
    }
}
