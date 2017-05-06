package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zykj.yixiu.R;
import com.zykj.yixiu.app.MyTopBar;
import com.zykj.yixiu.utils.ShouJi;

import org.xutils.x;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zykj on 2017/5/6.
 */

public class DingDanXiangQing_Activity extends Activity {
    @Bind(R.id.tou)
    MyTopBar tou;
    @Bind(R.id.dingdan_zhonglei)
    TextView dingdanZhonglei;
    @Bind(R.id.dingdan_name)
    TextView dingdanName;
    @Bind(R.id.dingdan_shouji)
    TextView dingdanShouji;
    @Bind(R.id.dingdan_shijian)
    TextView dingdanShijian;
    @Bind(R.id.dingdan_dizhi)
    TextView dingdanDizhi;
    @Bind(R.id.dingdan_tupian)
    ImageView dingdanTupian;
    @Bind(R.id.dingdan_pinpai)
    TextView dingdanPinpai;
    @Bind(R.id.dingdan_xinghao)
    TextView dingdanXinghao;
    @Bind(R.id.dingdan_guzhuang)
    TextView dingdanGuzhuang;
    @Bind(R.id.dingdan_miaoshu)
    TextView dingdanMiaoshu;
    private ShouJi shouJi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdanxiangqing);
        ButterKnife.bind(this);
        tou.setLeftClick(new MyTopBar.leftClick() {
            @Override
            public void Click(View view) {

            }
        });
        tou.setRightClick(new MyTopBar.rightClick() {
            @Override
            public void Click(View view) {
                Intent intent=new Intent(DingDanXiangQing_Activity.this,Main_Activity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();

        if (intent != null) {
            String phone = intent.getStringExtra("phone");
            String name = intent.getStringExtra("name");
            String shijian = intent.getStringExtra("shijian");
            String fuwudizhi = intent.getStringExtra("fuwudizhi");
            String file = intent.getStringExtra("file");
            String type1 = intent.getStringExtra("type1");
            shouJi=(ShouJi)intent.getSerializableExtra("type");
            switch (type1){
                case "A"://手机
                    dingdanZhonglei.setText("手机");
                    break;
                case "B"://家电
                    dingdanZhonglei.setText("家电");
                    break;
                case "C"://电脑
                    dingdanZhonglei.setText("电脑");
                    break;
            }
            dingdanName.setText(name);//姓名
            dingdanShouji.setText(phone);//电话
            dingdanShijian.setText(shijian);//时间
            dingdanDizhi.setText(fuwudizhi);//地址
            dingdanPinpai.setText(shouJi.getPINPAI());//品牌
            dingdanXinghao.setText(shouJi.getXINGHAO());//型号
            dingdanGuzhuang.setText(shouJi.getGUZHANG());//故障
            dingdanMiaoshu.setText(shouJi.getMIAOSHU());//故障描述
    }

    }
}
