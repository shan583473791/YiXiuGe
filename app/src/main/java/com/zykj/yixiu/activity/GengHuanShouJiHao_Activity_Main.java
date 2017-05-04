package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zykj.yixiu.R;
import com.zykj.yixiu.app.MyTopBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/20.
 */

public class GengHuanShouJiHao_Activity_Main extends Activity {
    @Bind(R.id.title_fanhui)
    MyTopBar titleFanhui;
    @Bind(R.id.botton_ok)
    Button bottonOk;
    @Bind(R.id.genghuanshoujihao_et_shoujihao)
    EditText genghuanshoujihaoEtShoujihao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genghuanshoujihao);
        ButterKnife.bind(this);
        titleFanhui.setLeftClick(new MyTopBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
    }

    @OnClick(R.id.botton_ok)
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.botton_ok:
                String trim = genghuanshoujihaoEtShoujihao.getText().toString().trim();
                Intent intent = new Intent();
                intent.putExtra("trim",trim);
                setResult(302, intent);
                finish();


                break;
        }
    }
}
