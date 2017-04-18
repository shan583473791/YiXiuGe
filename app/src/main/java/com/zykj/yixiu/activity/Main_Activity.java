package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.zykj.yixiu.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/18.
 */

public class Main_Activity extends Activity {
    @Bind(R.id.activity_main_tv_dizhi)
    TextView activityMainTvDizhi;
    @Bind(R.id.activity_main_iv_geren)
    ImageView activityMainIvGeren;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.activity_main_ll_shouji)
    LinearLayout activityMainLlShouji;
    @Bind(R.id.activity_main_ll_diannao)
    LinearLayout activityMainLlDiannao;
    @Bind(R.id.imageView2)
    ImageView imageView2;
    @Bind(R.id.activity_main_ll_jiadian)
    LinearLayout activityMainLlJiadian;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.activity_main_tv_dizhi, R.id.activity_main_iv_geren, R.id.activity_main_ll_shouji, R.id.activity_main_ll_diannao, R.id.activity_main_ll_jiadian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_main_tv_dizhi:
                //三级联动
                //第一级
                ArrayList<String> item1 = new ArrayList<>();
                item1.add("黑龙江");
                item1.add("辽宁");
                //二级
                final ArrayList<ArrayList<String>> item2 = new ArrayList<>();
                ArrayList<String> item2_1 = new ArrayList<>();
                item2_1.add("哈尔滨市");
                item2_1.add("大庆市");
                item2.add(item2_1);

                ArrayList<String> item2_2 = new ArrayList<>();
                item2_2.add("大连市");
                item2_2.add("沈阳市");
                item2.add(item2_2);
                //三级
                ArrayList<ArrayList<ArrayList<String>>> item3 = new ArrayList<>();
                OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        //把市级名称 显示到地址上
                        activityMainTvDizhi.setText(item2.get(options1).get(option2));
                    }
                })
                        .setSubmitText("确定")//确定按钮文字
                        .setCancelText("取消")//取消按钮文字
                        .setTitleText("城市选择")//标题
                        .setSubCalSize(18)//确定和取消文字大小
                        .setTitleSize(20)//标题文字大小
                        .setTitleColor(Color.BLACK)//标题文字颜色
                        .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                        .setCancelColor(Color.BLUE)//取消按钮文字颜色
                        .setTitleBgColor(0xFF333333)//标题背景颜色 Night mode
                        .setBgColor(0xFF000000)//滚轮背景颜色 Night mode
                        .setContentTextSize(18)//滚轮文字大小
                        .setLinkage(false)//设置是否联动，默认true
                        .setLabels("省", "市", "区")//设置选择的三级单位
                        .setCyclic(false, false, false)//循环与否
                        .setSelectOptions(1, 1, 1)  //设置默认选中项
                        .setOutSideCancelable(false)//点击外部dismiss default true
                        .isDialog(true)//是否显示为对话框样式
                        .build();
                pvOptions.setPicker(item1, item2);
                pvOptions.show();

                break;
            case R.id.activity_main_iv_geren:
                intent = new Intent(this,GeRenZhongXin_Activity_Main.class);
                startActivity(intent);
                break;
            case R.id.activity_main_ll_shouji:
                break;
            case R.id.activity_main_ll_diannao:
                break;
            case R.id.activity_main_ll_jiadian:
                break;
        }
    }
}
