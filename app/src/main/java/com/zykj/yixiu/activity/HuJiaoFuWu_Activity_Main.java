package com.zykj.yixiu.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.MyTopBar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static cn.finalteam.toolsfinal.DateUtils.calendar;

/**
 * Created by zykj on 2017/4/18.
 */

public class HuJiaoFuWu_Activity_Main extends Activity {
    @Bind(R.id.title_fanhui)
    MyTopBar titleFanhui;
    @Bind(R.id.hujiaofuwu_activity_ll_shijian)
    LinearLayout hujiaofuwuActivityLlShijian;
    @Bind(R.id.hujiaofuwu_activity_et_dizhi)
    EditText hujiaofuwuActivityEtDizhi;
    @Bind(R.id.hujiaofuwu_activity_iv_dizhi)
    ImageView hujiaofuwuActivityIvDizhi;
    @Bind(R.id.hujiaofuwu_activity_ok)
    Button hujiaofuwuActivityOk;
    @Bind(R.id.hujiao_tv_shijian)
    TextView hujiaoTvShijian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hujiaofuwu);
        ButterKnife.bind(this);
        titleFanhui.setLeftClick(new MyTopBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
    }

    @OnClick({R.id.hujiaofuwu_activity_ll_shijian, R.id.hujiaofuwu_activity_iv_dizhi, R.id.hujiaofuwu_activity_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hujiaofuwu_activity_ll_shijian:
                TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        hujiaoTvShijian.setText(date+"");
                    }
                })    .setType(TimePickerView.Type.ALL)//默认全部显示
                        .setCancelText("Cancel")//取消按钮文字
                        .setSubmitText("Sure")//确认按钮文字
                        .setContentSize(18)//滚轮文字大小
                        .setTitleSize(20)//标题文字大小
                        .setTitleText("Title")//标题文字
                        .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                        .isCyclic(true)//是否循环滚动
                        .setTitleColor(Color.BLACK)//标题文字颜色
                        .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                        .setCancelColor(Color.BLUE)//取消按钮文字颜色
                        .setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
                        .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
                        .setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR) + 20)//默认是1900-2100年
                        .setLabel("年","月","日","时","分","秒")
                        .isDialog(true)//是否显示为对话框样式
                        .build();
                pvTime.setDate(Calendar.getInstance());
                pvTime.show();



                break;
            case R.id.hujiaofuwu_activity_iv_dizhi:
                break;
            case R.id.hujiaofuwu_activity_ok:
                break;
        }
    }
}
