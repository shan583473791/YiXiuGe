package com.zykj.yixiu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zykj.yixiu.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zykj on 2017/5/4.
 */

public class WoDeDingDan_Activity_WeiWanCheng extends Activity {
    @Bind(R.id.wodedingdan_tv_weiwancheng)
    TextView wodedingdanTvWeiwancheng;
    @Bind(R.id.wodedingdan_tv_yiwancheng)
    TextView wodedingdanTvYiwancheng;
    @Bind(R.id.wodedingdan_tv_yiquxiao)
    TextView wodedingdanTvYiquxiao;
    @Bind(R.id.wodedingdan_iv_zuo)
    ImageView wodedingdanIvZuo;
    @Bind(R.id.wodedingdan_iv_zhong)
    ImageView wodedingdanIvZhong;
    @Bind(R.id.wodedingdan_iv_you)
    ImageView wodedingdanIvYou;
    @Bind(R.id.wodedingdan_lv)
    ListView wodedingdanLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wodedingdan);
        ButterKnife.bind(this);


    }
}
