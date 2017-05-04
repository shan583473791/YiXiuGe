package com.zykj.yixiu.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.MyTopBar;
import com.zykj.yixiu.utils.ShouJi;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import org.xutils.http.RequestParams;

import java.io.File;
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
    private Intent intent;
    private ShouJi shouJi;
    private  int type;
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
        Intent intent = getIntent();
        ;
        if ("shouji".equals(intent.getStringExtra("type"))){
            String s = intent.getStringExtra("ShouJi");

            shouJi =  JSONArray.parseObject(s, ShouJi.class);
            type=1;


        }else if ("jiadian".equals(intent.getStringExtra("type"))){
            String j = intent.getStringExtra("JiaDian");
            shouJi = JSONArray.parseObject(j, ShouJi.class);
            type=3;

        }else if ("diannao".equals(intent.getStringExtra("type"))){
            String d = intent.getStringExtra("DianNao");
            shouJi= JSONArray.parseObject(d, ShouJi.class);
            type=2;
        }
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
                intent = new Intent(this, DiZhiGuanLi_Activity_Main.class);
                startActivity(intent);
                break;
            case R.id.hujiaofuwu_activity_ok:
                //发布订单
                //需要的参数
                /*order_type: 订单类型,1手机,2电脑,3家电
                brand: 品牌
                model:型号
                fault:故障点
                fault_desc:故障描述
                category:类别 例如电脑(一体机,笔记本,台式机) 手机此参数为空
                image1:图片一  必选 必须有一张图片
                image2:图片二  可选
                image3:图片一   可选
                service_time:上门服务时间
                service_address:服务地址
                custom_phone:客户电话
                custom_name:客户姓名
                custom_id:客户ID
                address_id:客户关联的地址ID*/
                RequestParams params=new RequestParams(YURL.ADD_ORDER);
                File file=new File(shouJi.getTUPIAN());
                params.addBodyParameter("custom_name", Y.DIZHIGUANLI_USER.getName()); //custom_name:客户姓名
                params.addBodyParameter("brand",shouJi.getPINPAI()); //品牌
                params.addBodyParameter("model",shouJi.getXINGHAO()); //型号
                params.addBodyParameter("fault",shouJi.getMIAOSHU()); //故障描述
                params.addBodyParameter("image1",shouJi.getLEIXING()); //图片一  必选 必须有一张图片
                params.addBodyParameter("category",file); //类型
                params.addBodyParameter("fault_desc",shouJi.getGUZHANG()); //故障点
                params.addBodyParameter("order_type", type+""); //order_type: 订单类型,1手机,2电脑,3家电
                params.addBodyParameter("service_time",hujiaoTvShijian.getText().toString().trim()); //     service_time:上门服务时间
                params.addBodyParameter("service_address",hujiaofuwuActivityEtDizhi.getText().toString().trim()); //服务地址
                params.addBodyParameter("custom_phone", Y.DIZHIGUANLI_USER.getPhone()); //custom_name:客户电话
                params.addBodyParameter("address_id", Y.DIZHIGUANLI_USER.getAddress_id()+""); //address_id:客户关联的地址ID*/
                params.addBodyParameter("custom_id", Y.user.getUser_id()+"");// custom_id:客户ID
                Y.postFile(params, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.activity_dizhiguanli_itme,
                                (ViewGroup) findViewById(R.id.dialog));
                        new AlertDialog.Builder(HuJiaoFuWu_Activity_Main.this).setView(layout)
                                .setPositiveButton("确定", null)
                                .setNegativeButton("取消", null).show();
                        Y.t("上传成功");
                        finish();
                    }
                });

                break;
        }
    }
}
