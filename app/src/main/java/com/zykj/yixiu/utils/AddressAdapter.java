package com.zykj.yixiu.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.map.Text;
import com.zykj.yixiu.R;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 * 地址管理适配器
 *
 * Created by Administrator on 2017/4/17.
 */

public class AddressAdapter   extends  BaseAdapter{
    private List<DiZhiGuanLi_User> lists;  //数据源
    private Context context;  //上下文
    //初始化数据
    public AddressAdapter(List<DiZhiGuanLi_User> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }
    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder h =null;
        //优化
        if(convertView==null){
            convertView=View.inflate(context,R.layout.activity_dizhiguanli_itme,null);
//            h.itemAddressName= (TextView) convertView.findViewById(R.id.dizhiguanli_itme_name);
//            h.itemAddressPhone= (TextView) convertView.findViewById(R.id.dizhiguanli_itme_shoujihao);
//            h.itemAddressAdd= (TextView) convertView.findViewById(R.id.dizhiguanli_itme_dizhi);
//            h.itemTvAddress= (TextView) convertView.findViewById(R.id.item_tv_address);
//            h.itemIvAddress= (ImageView) convertView.findViewById(R.id.item_iv_address);
//            h.itemAddressBtBianji= (Button) convertView.findViewById(R.id.dizhiguanli_itme_bianji);
//            h.itemAddressBtShanchu= (Button) convertView.findViewById(R.id.dizhiguanli_itme_shanchu);
            h =new ViewHolder(convertView);
            convertView.setTag(h);
        }else{
            h= (ViewHolder) convertView.getTag();
        }

        //设置数据
        DiZhiGuanLi_User a=lists.get(position);
        h.itemAddressName.setText(a.getName());
        h.itemAddressPhone.setText(a.getPhone());
        h.itemAddressAdd.setText(a.getAddress());

        // 检测默认
        if(a.getIsdefault()==1){ // 勾选
            h.itemTvAddress.setText("默认");
            h.itemTvAddress.setTextColor(0xff00cccc);
            h.itemIvAddress.setImageResource( R.mipmap.landuihao);  //设置背景勾选
        }else{          //不勾选

            h.itemTvAddress.setText("设置");
            h.itemTvAddress.setTextColor(0xffcacaca);
            h.itemIvAddress.setImageResource(R.mipmap.heixianquan);  //背景不勾选
        }

        //选择默认事件
        h.itemIvAddress.setTag(a.getAddress_id());//把ID 捆绑到itemAddressBtShanchu 控件上
        h.itemIvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map map =new HashMap();
                map.put("user_id",Y.user.getUser_id()+"");
                map.put("address_id",v.getTag()+"");
                //发送删除请求
                Y.get(YURL.DEF_ADDRESS, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Y.dismiss();//关闭对话框
                        Y.t("设置成功");
                        if(Y.getRespCode(result)){
                            for (int i = 0; i <lists.size() ; i++) {
                                    if(i==position){  //需要勾选的位置
                                        lists.get(i).setIsdefault(1);
                                    }else{  //取消勾选
                                        lists.get(i).setIsdefault(0);
                                    }
                            }
                            notifyDataSetChanged();//刷新列表


                        }
                    }
                });
            }
        });



        //删除
        h.itemAddressBtShanchu.setTag(a.getAddress_id());//把ID 捆绑到itemAddressBtShanchu 控件上
        h.itemAddressBtShanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map map =new HashMap();
                map.put("user_id",Y.user.getUser_id()+"");
                map.put("address_id",v.getTag()+"");
                  //发送删除请求
                Y.get(YURL.DEL_ADDRESS, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Y.dismiss();//关闭对话框
                        if(Y.getRespCode(result)){
                            Y.t("删除成功");
                            lists.remove(position);// 删除数据源得到数据
                            notifyDataSetChanged();  //刷新列表
                        }
                    }
                });


            }
        });
        return convertView;

    }
    static class ViewHolder {
        @Bind(R.id.dizhiguanli_itme_name)
        TextView itemAddressName;
        @Bind(R.id.dizhiguanli_itme_shoujihao)
        TextView itemAddressPhone;
        @Bind(R.id.dizhiguanli_itme_dizhi)
        TextView itemAddressAdd;
        @Bind(R.id.item_iv_address)
        ImageView itemIvAddress;
        @Bind(R.id.item_tv_address)
        TextView itemTvAddress;
        @Bind(R.id.dizhiguanli_itme_bianji)
        Button itemAddressBtBianji;
        @Bind(R.id.dizhiguanli_itme_shanchu)
        Button itemAddressBtShanchu;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
