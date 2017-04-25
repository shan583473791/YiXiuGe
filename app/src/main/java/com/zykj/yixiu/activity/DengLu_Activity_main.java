package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.hss01248.dialog.StyledDialog;
import com.umeng.analytics.MobclickAgent;
import com.zykj.yixiu.R;
import com.zykj.yixiu.utils.User;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/8.
 */

public class DengLu_Activity_main extends Activity {
    @Bind(R.id.activity_denglu_et_shoujihao)
    EditText activityDengluEtShoujihao;
    @Bind(R.id.activity_denglu_et_mima)
    EditText activityDengluEtMima;
    @Bind(R.id.activity_denglu_bt_denglu)
    Button activityDengluBtDenglu;
    @Bind(R.id.activity_denglu_tv_zhuche)
    TextView activityDengluTvZhuche;
    @Bind(R.id.activity_denglu_tv_wangjimima)
    TextView activityDengluTvWangjimima;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu_main);
        ButterKnife.bind(this);
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
    }

    @OnClick({R.id.activity_denglu_bt_denglu, R.id.activity_denglu_tv_zhuche, R.id.activity_denglu_tv_wangjimima})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_denglu_bt_denglu:

                String shoujihao = activityDengluEtShoujihao.getText().toString().trim();
                String mima = activityDengluEtMima.getText().toString().trim();
                Map<String, String> map = new HashMap<>();
                map.put("phone", shoujihao);
                map.put("password", mima);
                Y.get(YURL.LOGIN, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            Y.t("登录成功");
                           Y.user = JSON.parseObject(Y.getData(result), User.class);
                            startActivity(new Intent(DengLu_Activity_main.this,Main_Activity.class));
                        }
                    }
                });

                break;
            case R.id.activity_denglu_tv_zhuche:
                intent = new Intent(this,DengLu_Activity_ZhuCe.class);
                startActivity(intent);
                break;
            case R.id.activity_denglu_tv_wangjimima:
                intent = new Intent(this,DengLu_Activity_WangJiMiMa.class);
                startActivity(intent);
                break;
        }
    }
}
