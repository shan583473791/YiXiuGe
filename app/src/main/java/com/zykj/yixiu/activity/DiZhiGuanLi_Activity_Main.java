package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.MyAdapter;
import com.zykj.yixiu.app.MyTopBar;
import com.zykj.yixiu.utils.DiZhiGuanLi_User;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/20.
 */

public class DiZhiGuanLi_Activity_Main extends Activity {
    @Bind(R.id.title_fanhui)
    MyTopBar titleFanhui;
    @Bind(R.id.dizhiguanli_activity_xinzengdizhi)
    LinearLayout dizhiguanliActivityXinzengdizhi;
    @Bind(R.id.dizhiguanli_lv)
    ListView dizhiguanliLv;
    private Intent intent;
    private List<DiZhiGuanLi_User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dizhiguanli);
        ButterKnife.bind(this);
        titleFanhui.setLeftClick(new MyTopBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        Map map  =new HashMap();
        map.put("user_id", Y.user.getUser_id()+"");


        Y.get(YURL.SELECT_ADDRESS, map, new Y.MyCommonCall<String>() {
            @Override
            public void onSuccess(String result) {
                StyledDialog.dismissLoading();
                if(Y.getRespCode(result)){
                    userList= JSON.parseArray(Y.getData(result), DiZhiGuanLi_User.class);
                   MyAdapter myAdapter=new MyAdapter(DiZhiGuanLi_Activity_Main.this,userList);
                    dizhiguanliLv.setAdapter(myAdapter);
                }
            }
        });
    }

    @OnClick(R.id.dizhiguanli_activity_xinzengdizhi)
    public void onViewClicked() {
        intent = new Intent(this, BianJiDiZhi_Activity_Main.class);
        startActivity(intent);
    }
}
