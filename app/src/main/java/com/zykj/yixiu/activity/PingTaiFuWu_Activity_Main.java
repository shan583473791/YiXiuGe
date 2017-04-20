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

public class PingTaiFuWu_Activity_Main extends Activity {
    @Bind(R.id.title_fanhui)
    MyTopBar titleFanhui;
    @Bind(R.id.pingtaifuwu_activity_zixin)
    LinearLayout pingtaifuwuActivityZixin;
    @Bind(R.id.pingtaifuwu_activity_changjianwenti)
    LinearLayout pingtaifuwuActivityChangjianwenti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pingtaifuwu);
        ButterKnife.bind(this);
        titleFanhui.setLeftClick(new MyTopBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
    }

    @OnClick({R.id.pingtaifuwu_activity_zixin, R.id.pingtaifuwu_activity_changjianwenti})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pingtaifuwu_activity_zixin:
                startActivity(new Intent(PingTaiFuWu_Activity_Main.this,YiXiuGeZiXun_PingTaiFuWu_activity.class));
                break;
            case R.id.pingtaifuwu_activity_changjianwenti:
                break;
        }
    }
}
