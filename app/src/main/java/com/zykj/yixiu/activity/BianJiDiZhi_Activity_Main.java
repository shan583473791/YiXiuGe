package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.MyTopBar;
import com.zykj.yixiu.utils.DiZhiGuanLi_User;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import org.xutils.http.RequestParams;

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
    @Bind(R.id.botton_ok)
    Button bottonOk;
    @Bind(R.id.bianjidizhi_activity_tv_shoujihao)
    TextView bianjidizhiActivityTvShoujihao;
    private Intent intent;
    private String dizhi, qu, trim;
    private String wei;
    private String jing, bianma;


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
                intent = new Intent(this, GengHuanShouJiHao_Activity_Main.class);
                startActivityForResult(intent, 301);
                break;
            case R.id.bianjidizhi_activity_dizhi:
                intent = new Intent(this, BaiDuDiTu.class);
                startActivityForResult(intent, 100);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 101) {
            Bundle extras = data.getExtras();
            dizhi = extras.getString("dizhi");
            qu = extras.getString("qu");
            wei = extras.getString("wei");
            jing = extras.getString("jing");
            bianma = extras.getString("dizhibianma");
            editText.setText(dizhi);
        }
        if (requestCode == 301 && resultCode == 302) {
            Bundle extras = data.getExtras();
            trim = extras.getString("trim");
            bianjidizhiActivityTvShoujihao.setText(trim);
        }
    }

    @OnClick(R.id.botton_ok)
    public void onViewClicked() {

//        name: 姓名
//        address: 地址
//        phone: 电话号码
//        user_id:用户ID
//        region:区
//        lat:纬度
//        lon:经度
//        city_name:城市名
//        city_code:城市编码
//        isdefault: 是否是默认 0 不默认  1 默认

        RequestParams params = new RequestParams(YURL.ADDADDRESS);
        params.addBodyParameter("name", editText.getText().toString().trim());
        params.addBodyParameter("address", Y.user_tianJia.getAddress());
        params.addBodyParameter("phone", textView2.getText().toString());
        params.addBodyParameter("user_id", Y.user.getUser_id() + "");
        params.addBodyParameter("region", Y.user_tianJia.getRegion());
        params.addBodyParameter("lat", Y.user_tianJia.getLat());
        params.addBodyParameter("lon", Y.user_tianJia.getLon());
        params.addBodyParameter("city_name", Y.user.getCity());
        params.addBodyParameter("city_code", Y.user_tianJia.getCity_code());
        params.addBodyParameter("isdefault", 1 + "");
        Y.post(null, new Y.MyCommonCall<String>() {
            @Override
            public void onSuccess(String result) {
                Y.dismiss();
            }
        });
        finish();
    }
}
