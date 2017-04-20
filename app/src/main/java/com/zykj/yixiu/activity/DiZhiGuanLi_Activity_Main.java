package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.zykj.yixiu.R;
import com.zykj.yixiu.app.MyTopBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/20.
 */

public class DiZhiGuanLi_Activity_Main extends Activity {
    @Bind(R.id.title_fanhui)
    MyTopBar titleFanhui;
    @Bind(R.id.dizhiguanli_activity_xinzengdizhi)
    LinearLayout dizhiguanliActivityXinzengdizhi;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dizhiguanli);
        ButterKnife.bind(this);
        titleFanhui.setLeftClick(new MyTopBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
    }

    @OnClick(R.id.dizhiguanli_activity_xinzengdizhi)
    public void onViewClicked() {
        intent = new Intent(this,BianJiDiZhi_Activity_Main.class);
        startActivity(intent);
    }
}
