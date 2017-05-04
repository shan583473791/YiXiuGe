package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.MyAdapter;
import com.zykj.yixiu.app.MyTopBar;
import com.zykj.yixiu.utils.AddressAdapter;
import com.zykj.yixiu.utils.DiZhiGuanLi_User;
import com.zykj.yixiu.utils.User;
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
    private Intent intent1;

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
        dizhiguanliLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String address = userList.get(position).getAddress();
                String name = userList.get(position).getName();
                int address_id = userList.get(position).getAddress_id();
                String phone = userList.get(position).getPhone();
                intent1 = new Intent();
                intent1.putExtra("address", address);
                intent1.putExtra("name", name);
                intent1.putExtra("phone", phone);
                intent1.putExtra("address_id", address_id);
                Y.i(address);
                Y.i(name);
                Y.i(address_id+"");
                Y.i(phone);
                setResult(112, intent1);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Map map = new HashMap();
        map.put("user_id", Y.user.getUser_id() + "");
        Y.get(YURL.SELECT_ADDRESS, map, new Y.MyCommonCall<String>() {
            @Override
            public void onSuccess(String result) {
                Y.dismiss();
                if (Y.getRespCode(result)) {
                    userList = JSON.parseArray(Y.getData(result), DiZhiGuanLi_User.class);
                    //MyAdapter myAdapter=new MyAdapter(DiZhiGuanLi_Activity_Main.this,userList);
                    //  dizhiguanliLv.setAdapter(myAdapter);
                    //  diZhiGuanLi_user = JSON.parseObject(Y.getData(result), DiZhiGuanLi_User.class);
                  /*  intent1 = new Intent();
                    intent1.putExtra("name", diZhiGuanLi_user.getName());
                    intent1.putExtra("phone", diZhiGuanLi_user.getPhone());
                    intent1.putExtra("address_id", diZhiGuanLi_user.getAddress_id());
                    setResult(112, intent1);*/
                    //启动适配器
                    dizhiguanliLv.setAdapter(new AddressAdapter(userList, DiZhiGuanLi_Activity_Main.this));
                    // finish();
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
