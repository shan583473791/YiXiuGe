package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.MyTopBar;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import java.util.HashMap;
import java.util.Map;

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
    @Bind(R.id.title_fanhui)
    MyTopBar titleFanhui;
    @Bind(R.id.zhuce_shoujihao)
    EditText zhuceShoujihao;
    @Bind(R.id.zhuce_yanzhengma)
    EditText zhuceYanzhengma;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        ButterKnife.bind(this);
        titleFanhui.setLeftClick(new MyTopBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });

    }

    @OnClick({R.id.activity_denglu_zhuce_bt_yanzhengma, R.id.activity_denglu_zhuce_bt_zhuce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_denglu_zhuce_bt_yanzhengma:
                break;
            case R.id.activity_denglu_zhuce_bt_zhuce:
                String shoujihao = zhuceShoujihao.getText().toString().trim();
                String yanzhengma = zhuceYanzhengma.getText().toString().trim();
                Map<String, String> map = new HashMap<String, String>();
                map.put("phone", shoujihao);
                map.put("vcode", yanzhengma);
                map.put("type", 0 + "");
                Y.get(YURL.REGISTER, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            String data = Y.getData(result);
                            Intent zhuCeIntent = new Intent(getApplicationContext(), DengLu_Activity_MiMa.class);
                            zhuCeIntent.putExtra("token", data);
                            startActivity(zhuCeIntent);
                        } else {
                            Y.t(JSON.parseObject(result).getString("message"));
                        }

                    }
                });


                break;
        }
    }
}
