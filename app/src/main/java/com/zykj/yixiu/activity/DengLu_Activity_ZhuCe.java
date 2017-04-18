package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zykj.yixiu.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/18.
 */

public class DengLu_Activity_ZhuCe extends Activity {
    @Bind(R.id.activity_denglu_zhuce_bt_yanzhengma)
    Button activityDengluZhuceBtYanzhengma;
    @Bind(R.id.activity_denglu_zhuce_bt_zhuce)
    Button activityDengluZhuceBtZhuce;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.activity_denglu_zhuce_bt_yanzhengma, R.id.activity_denglu_zhuce_bt_zhuce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_denglu_zhuce_bt_yanzhengma:
                break;
            case R.id.activity_denglu_zhuce_bt_zhuce:
                intent = new Intent(this,DengLu_Activity_MiMa.class);
                startActivity(intent);
                break;
        }
    }
}
