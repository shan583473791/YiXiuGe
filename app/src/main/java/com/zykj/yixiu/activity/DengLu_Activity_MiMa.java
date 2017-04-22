package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
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

public class DengLu_Activity_MiMa extends Activity {
    @Bind(R.id.mima_et_mima)
    EditText mimaEtMima;
    @Bind(R.id.mima_et_chongmima)
    EditText mimaEtChongmima;
    @Bind(R.id.mima_bt_ok)
    Button mimaBtOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mima);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.mima_bt_ok)
    public void onViewClicked() {
        String mima1 = mimaEtMima.getText().toString().trim();
        String mima2 = mimaEtChongmima.getText().toString().trim();
        if (TextUtils.isEmpty(mima1)&&TextUtils.isEmpty(mima2)){
            Y.t("请输入密码");
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("password",mima2);
        map.put("token",Y.user.getToken());
        Y.get(YURL.SETPASSWORD, map, new Y.MyCommonCall<String>() {
            @Override
            public void onSuccess(String result) {
                StyledDialog.dismissLoading();
                if (Y.getRespCode(result)) {
                    Y.t("成功");
                    startActivity(new Intent(DengLu_Activity_MiMa.this,DengLu_Activity_main.class));
                }
                Y.t("失败");
            }
        });

    }
}
