package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zykj.yixiu.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/18.
 */

public class GeRenZhongXin_Activity_Main extends Activity {
    @Bind(R.id.gerenzhongxin_activity_fanhui)
    ImageView gerenzhongxinActivityFanhui;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.grzx_activity_weiwancheng)
    LinearLayout grzxActivityWeiwancheng;
    @Bind(R.id.grzx_activity_yiwancheng)
    LinearLayout grzxActivityYiwancheng;
    @Bind(R.id.grzx_activity_yiquxiao)
    LinearLayout grzxActivityYiquxiao;
    @Bind(R.id.grzx_activity_wodeziliao)
    LinearLayout grzxActivityWodeziliao;
    @Bind(R.id.grzx_activity_wodeqianbao)
    LinearLayout grzxActivityWodeqianbao;
    @Bind(R.id.grzx_activity_dizhiguanli)
    LinearLayout grzxActivityDizhiguanli;
    @Bind(R.id.grzx_activity_renzhengxinxi)
    LinearLayout grzxActivityRenzhengxinxi;
    @Bind(R.id.grzx_activity_pingtaifuwu)
    LinearLayout grzxActivityPingtaifuwu;
    @Bind(R.id.textView3)
    TextView textView3;
    @Bind(R.id.grzx_activity_guanyuwomen)
    LinearLayout grzxActivityGuanyuwomen;
    @Bind(R.id.grzx_activity_shezhi)
    LinearLayout grzxActivityShezhi;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenzhongxin);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.gerenzhongxin_activity_fanhui, R.id.grzx_activity_weiwancheng, R.id.grzx_activity_yiwancheng, R.id.grzx_activity_yiquxiao, R.id.grzx_activity_wodeziliao, R.id.grzx_activity_wodeqianbao, R.id.grzx_activity_dizhiguanli, R.id.grzx_activity_renzhengxinxi, R.id.grzx_activity_pingtaifuwu, R.id.grzx_activity_guanyuwomen, R.id.grzx_activity_shezhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.gerenzhongxin_activity_fanhui:
                finish();
                break;
            case R.id.grzx_activity_weiwancheng:


                break;
            case R.id.grzx_activity_yiwancheng:
                break;
            case R.id.grzx_activity_yiquxiao:
                break;
            case R.id.grzx_activity_wodeziliao:
                intent = new Intent(this,WoDeZiLiao_Activity_Main.class);
                startActivity(intent);
                break;
            case R.id.grzx_activity_wodeqianbao:
                intent = new Intent(this,WoDeQianBao_Activity_Main.class);
                startActivity(intent);
                break;
            case R.id.grzx_activity_dizhiguanli:
                intent = new Intent(this,DiZhiGuanLi_Activity_Main.class);
                startActivity(intent);
                break;
            case R.id.grzx_activity_renzhengxinxi:
                intent = new Intent(this,RenZhengXinXi_Activity_Main.class);
                startActivity(intent);
                break;
            case R.id.grzx_activity_pingtaifuwu:
                intent = new Intent(this,PingTaiFuWu_Activity_Main.class);
                startActivity(intent);
                break;
            case R.id.grzx_activity_guanyuwomen:
                break;
            case R.id.grzx_activity_shezhi:
                break;
        }
    }
}
