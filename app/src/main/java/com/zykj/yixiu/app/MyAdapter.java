package com.zykj.yixiu.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zykj.yixiu.R;
import com.zykj.yixiu.utils.DiZhiGuanLi_User;

import java.util.List;

/**
 * Created by zykj on 2017/4/27.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<DiZhiGuanLi_User> diZhiGuanLiUsers;

    public MyAdapter(Context context, List<DiZhiGuanLi_User> diZhiGuanLiUsers) {
        this.context = context;
        this.diZhiGuanLiUsers = diZhiGuanLiUsers;
    }

    @Override
    public int getCount() {
        return diZhiGuanLiUsers.size();
    }
    @Override
    public Object getItem(int position) {
        return diZhiGuanLiUsers.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder=null;
        if (convertView==null){
            convertView=View.inflate(context, R.layout.activity_dizhiguanli_itme,null);
            myViewHolder=new MyViewHolder();
            myViewHolder.tv_dizhi.findViewById(R.id.dizhiguanli_itme_dizhi);
            myViewHolder.tv_shoujihao.findViewById(R.id.dizhiguanli_itme_shoujihao);
            myViewHolder.tv_name.findViewById(R.id.dizhiguanli_itme_name);
            convertView.setTag(myViewHolder);
        }else {
            myViewHolder= (MyViewHolder) convertView.getTag();
        }
        return convertView;
    }
    class MyViewHolder{
        TextView tv_name,tv_shoujihao,tv_dizhi;
    }

}

