package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.zykj.yixiu.R;
import com.zykj.yixiu.app.MyTopBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/20.
 */

public class BianJiDiZhi_Activity_Main extends Activity {
    @Bind(R.id.title_fanhui)
    MyTopBar titleFanhui;
    @Bind(R.id.textView2)
    TextView textView2;
    @Bind(R.id.editText)
    EditText editText;
    @Bind(R.id.bianjidizhi_activity_dizhi)
    ImageView bianjidizhiActivityDizhi;
    @Bind(R.id.switch2)
    Switch switch2;
    @Bind(R.id.bianjidizhi_activity_ll_shoujihao)
    LinearLayout bianjidizhiActivityLlShoujihao;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bianjidizhi);
        ButterKnife.bind(this);
        titleFanhui.setLeftClick(new MyTopBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
    }


    @OnClick({R.id.bianjidizhi_activity_ll_shoujihao, R.id.bianjidizhi_activity_dizhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bianjidizhi_activity_ll_shoujihao:
                intent=new Intent(this,GengHuanShouJiHao_Activity_Main.class);
                startActivity(intent);
                break;
            case R.id.bianjidizhi_activity_dizhi:
                intent=new Intent(this,BaiDuDiTu.class);
                startActivity(intent);
                break;
        }
    }
}
