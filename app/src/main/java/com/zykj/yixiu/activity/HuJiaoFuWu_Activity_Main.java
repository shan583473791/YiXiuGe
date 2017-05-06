package com.zykj.yixiu.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
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
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

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
    private int type;
    private String name, address;
    private String phone;
    private Object address_id;
    private String type1;

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

        if (intent != null) {
            type1 = intent.getStringExtra("type");
            switch (type1) {
                case "A"://手机
                    shouJi = (ShouJi) intent.getSerializableExtra("Bean");
                    Y.i(shouJi.getPINPAI() + "品牌");
                    Y.i(shouJi.getLEIXING() + "类型");
                    Y.i(shouJi.getXINGHAO() + "型号");
                    Y.i(shouJi.getGUZHANG() + "故障");
                    Y.i(shouJi.getMIAOSHU() + "描述");
                    Y.i(shouJi.getTUPIAN() + "图片");
                    break;
                case "B"://家电
                    shouJi = (ShouJi) intent.getSerializableExtra("Bean");
                    Y.i(shouJi.getPINPAI() + "品牌");
                    Y.i(shouJi.getLEIXING() + "类型");
                    Y.i(shouJi.getXINGHAO() + "型号");
                    Y.i(shouJi.getGUZHANG() + "故障");
                    Y.i(shouJi.getMIAOSHU() + "描述");
                    Y.i(shouJi.getTUPIAN() + "图片");
                    break;
                case "C"://电脑
                    shouJi = (ShouJi) intent.getSerializableExtra("Bean");
                    Y.i(shouJi.getPINPAI() + "品牌");
                    Y.i(shouJi.getLEIXING() + "类型");
                    Y.i(shouJi.getXINGHAO() + "型号");
                    Y.i(shouJi.getGUZHANG() + "故障");
                    Y.i(shouJi.getMIAOSHU() + "描述");
                    Y.i(shouJi.getTUPIAN() + "图片");
                    break;
            }

        }

    }

    @OnClick({R.id.hujiaofuwu_activity_ll_shijian, R.id.hujiaofuwu_activity_iv_dizhi, R.id.hujiaofuwu_activity_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hujiaofuwu_activity_ll_shijian:
                TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
                        hujiaoTvShijian.setText(simpleDateFormat.format(date));

                    }
                })
                        .build();
                pvTime.setDate(Calendar.getInstance());
                pvTime.show();
                break;
            case R.id.hujiaofuwu_activity_iv_dizhi:
                intent = new Intent(this, DiZhiGuanLi_Activity_Main.class);
                startActivityForResult(intent, 111);
                break;
            case R.id.hujiaofuwu_activity_ok:
                final String shijan = hujiaoTvShijian.getText().toString().trim();//时间
                final String fuwudizhi = hujiaofuwuActivityEtDizhi.getText().toString().trim();//服务地址
                File file = new File(shouJi.getTUPIAN());
                Luban.get(HuJiaoFuWu_Activity_Main.this).load(file).putGear(Luban.THIRD_GEAR).setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                    }
                    @Override
                    public void onSuccess(final File file) {
                        Y.i("压缩后" + file.length());
                        RequestParams params = new RequestParams(YURL.ADD_ORDER);
                        params.addBodyParameter("custom_name", name); //custom_name:客户姓名
                        params.addBodyParameter("brand", shouJi.getPINPAI()); //品牌
                        params.addBodyParameter("model", shouJi.getXINGHAO()); //型号
                        params.addBodyParameter("fault", shouJi.getMIAOSHU()); //故障描述
                        params.addBodyParameter("image1", file); //图片一  必选 必须有一张图片
                        params.addBodyParameter("category", shouJi.getLEIXING()); //类型
                        params.addBodyParameter("fault_desc", shouJi.getGUZHANG()); //故障点
                        params.addBodyParameter("order_type", type + ""); //order_type: 订单类型,1手机,2电脑,3家电
                        params.addBodyParameter("service_time", shijan); //     service_time:上门服务时间
                        params.addBodyParameter("service_address", fuwudizhi); //服务地址
                        params.addBodyParameter("custom_phone", phone); //custom_name:客户电话
                        params.addBodyParameter("address_id", address_id + ""); //address_id:客户关联的地址ID*/
                        params.addBodyParameter("custom_id", Y.user.getUser_id() + "");// custom_id:客户ID
                        Y.post(params, new Y.MyCommonCall<String>() {
                            @Override
                            public void onSuccess(String result) {
                                Y.dismiss();
                                if (Y.getRespCode(result)) {
                                    final Dialog dialog = new Dialog(HuJiaoFuWu_Activity_Main.this, R.style.simple_dialog);
                                    final View inflate = LayoutInflater.from(HuJiaoFuWu_Activity_Main.this).inflate(R.layout.dialog_call_service, null);
                                    TextView xiangqing = (TextView) inflate.findViewById(R.id.dialog_tv_qing);
                                    TextView ok = (TextView) inflate.findViewById(R.id.dialog_tv_ok);
                                    xiangqing.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(HuJiaoFuWu_Activity_Main.this, DingDanXiangQing_Activity.class);
                                            intent.putExtra("type", shouJi);
                                            intent.putExtra("type1", type1);
                                            intent.putExtra("shijian", shijan);//时间
                                            intent.putExtra("fuwudizhi", fuwudizhi);//服务地址
                                            intent.putExtra("phone", phone);//电话
                                            intent.putExtra("file", file);//图片
                                            intent.putExtra("name", name);//图片
                                            startActivity(intent);
                                            dialog.dismiss();
                                            finish();
                                        }
                                    });
                                    ok.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent=new Intent(HuJiaoFuWu_Activity_Main.this, Main_Activity.class);
                                            dialog.dismiss();
                                            finish();
                                        }
                                    });
                                    dialog.setContentView(inflate);
                                    Window window = dialog.getWindow();
                                    WindowManager m = getWindowManager();
                                    Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
                                    WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
                                    p.height = (int) (d.getHeight() * 0.2); // 改变的是dialog框在屏幕中的位置而不是大小
                                    p.width = (int) (d.getWidth() * 0.65); // 宽度设置为屏幕的0.65
                                    window.setAttributes(p);
                                    dialog.show();
                                } else {

                                }
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }).launch();


                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111 && resultCode == 112) {
            Bundle extras = data.getExtras();
            name = extras.getString("name");
            phone = extras.getString("phone");
            address_id = extras.get("address_id");
            address = extras.getString("address");
            hujiaofuwuActivityEtDizhi.setText(address);

        }

    }

}
