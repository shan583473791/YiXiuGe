package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.MobileBean;
import com.zykj.yixiu.app.MyTopBar;
import com.zykj.yixiu.utils.ShouJi;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import org.xutils.http.RequestParams;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.widget.GFImageView;

/**
 * Created by zykj on 2017/4/18.
 */

public class ShouJiWeiXiu_Activity_Main extends Activity {
    private final int REQUEST_CODE_CAMERA = 1000; //相机表示
    private final int REQUEST_CODE_GALLERY = 1001; //相册标示
    private final int REQUEST_CODE_CROP = 1002;    //裁剪表示
    private final int REQUEST_CODE_EDIT = 1003;       //编辑表示

    List<MobileBean> lists; //品牌的数据源
    int mobileIndex = -1;  //用于检测是否选择了品牌
    @Bind(R.id.shouji_tv_pinpai)
    TextView shoujiTvPinpai;
    @Bind(R.id.shouji_activity_ll_pinpai)
    LinearLayout shoujiActivityLlPinpai;
    @Bind(R.id.shouji_tv_xinghao)
    TextView shoujiTvXinghao;
    @Bind(R.id.shouji_activity_ll_xinghao)
    LinearLayout shoujiActivityLlXinghao;
    @Bind(R.id.shouji_tv_guzhang)
    TextView shoujiTvGuzhang;
    @Bind(R.id.shouji_activity_ll_guzhang)
    LinearLayout shoujiActivityLlGuzhang;
    @Bind(R.id.shouji_et_miaoshu)
    EditText shoujiEtMiaoshu;
    @Bind(R.id.shouji_iv_di)
    ImageView shoujiIvDi;
    @Bind(R.id.shouji_iv_zhong)
    ImageView shoujiIvZhong;
    @Bind(R.id.shouji_iv_xiao)
    ImageView shoujiIvXiao;
    @Bind(R.id.shouji_fl_tu)
    FrameLayout shoujiFlTu;
    @Bind(R.id.botton_ok)
    Button bottonOk;
    @Bind(R.id.title_fanhui)
    MyTopBar titleFanhui;
    private ThemeConfig themeConfig;
    private OptionsPickerView opv;
    private Intent intent;
    private ShouJi shouJi;
    private String photoPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoujiweixiu);
        ButterKnife.bind(this);
        themeConfig = new ThemeConfig.Builder().build();
        titleFanhui.setLeftClick(new MyTopBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
    }
    @OnClick({R.id.shouji_activity_ll_pinpai, R.id.shouji_activity_ll_xinghao, R.id.shouji_activity_ll_guzhang, R.id.shouji_fl_tu, R.id.botton_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shouji_activity_ll_pinpai: //选择品牌
                //发起请求
                Y.get(YURL.FIND_PHONE_BRAND,null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if (Y.getRespCode(result)) {
                            StyledDialog.dismissLoading();
                            //成功
                            lists = JSON.parseArray(Y.getData(result), MobileBean.class);
                            //创建选择器
                            //选择后的监听器
                            // 当前选择的索引
                            if (opv==null)
                            opv = new OptionsPickerView.Builder(ShouJiWeiXiu_Activity_Main.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                    //选择后的监听器
                                    shoujiTvPinpai.setText(lists.get(options1).getName());
                                    if ( mobileIndex != options1){

                                        mobileIndex = options1; // 当前选择的索引
                                        //如果从选型号 把下面清空
                                        shoujiTvXinghao.setHint("请选择您的手机型号");
                                        shoujiTvXinghao.setText("");
                                        shoujiTvGuzhang.setHint("请选择您手机的故障");
                                        shoujiTvGuzhang.setText("");

                                    }

                                    mobileIndex = options1; // 当前选择的索引
                                }
                            }).build();
                            //把lists 进行转换
                            List<String> strs = new ArrayList<String>();
                            for (MobileBean mb : lists) {
                                strs.add(mb.getName());
                            }
                            //添加数据
                            opv.setPicker(strs, null, null);
                            //显示选择器
                            if (!opv.isShowing()) {
                                opv.show();
                            }
                        } else {
                            //失败
                            Y.t("数据解析失败");
                        }
                    }
                });
                break;
            case R.id.shouji_activity_ll_xinghao:
                if (mobileIndex == -1) {
                    Y.t("请您先选择品牌");
                } else {
                    //开始获取型号数据
                    //发起请求
                    Map map=new HashMap();
                    map.put("pid",lists.get(mobileIndex).getId()+"");
                    Y.get(YURL.FIND_PHONE_MODEL,map ,new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {
                                //成功
                                lists = JSON.parseArray(Y.getData(result), MobileBean.class);
                                //创建选择器
                         opv = new OptionsPickerView.Builder(ShouJiWeiXiu_Activity_Main.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        shoujiTvXinghao.setText(lists.get(options1).getName());
                                        if ( mobileIndex != options1){

                                            mobileIndex = options1; // 当前选择的索引
                                            //如果从选型号 把下面清空
                                            shoujiTvGuzhang.setHint("请选择您手机的故障");
                                            shoujiTvGuzhang.setText("");

                                        }

                                        mobileIndex = options1; // 当前选择的索引
                                    }
                                }).build();
                                //把lists 进行转换
                                List<String> strs = new ArrayList<String>();
                                for (MobileBean mb : lists) {
                                    strs.add(mb.getName());
                                }
                                //添加数据
                                opv.setPicker(strs, null, null);
                                //显示选择器
                                //显示选择器
                                if (!opv.isShowing()) {
                                    opv.show();
                                }
                            } else {
                                //失败
                                Y.t("数据解析失败");
                            }
                        }
                    });
                }
                break;
            case R.id.shouji_activity_ll_guzhang:
                //发起请求
                Y.get(YURL.FIND_PHONE_FAULT,null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if (Y.getRespCode(result)) {
                            StyledDialog.dismissLoading();
                            //成功
                            lists = JSON.parseArray(Y.getData(result), MobileBean.class);
                            //创建选择器
                            opv = new OptionsPickerView.Builder(ShouJiWeiXiu_Activity_Main.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                    //选择后的监听器
                                    shoujiTvGuzhang.setText(lists.get(options1).getName());


                                }
                            }).build();
                            //把lists 进行转换
                            List<String> strs = new ArrayList<String>();
                            for (MobileBean mb : lists) {
                                strs.add(mb.getName());
                            }
                            //添加数据
                            opv.setPicker(strs, null, null);
                            //显示选择器
                            if (! opv.isShowing()){
                                opv.show();
                            }
                        } else {
                            //失败
                            Y.t("数据解析失败");
                        }
                    }
                });
                break;
            case R.id.shouji_fl_tu:
                FunctionConfig functionConfig = new FunctionConfig.Builder().build();
                CoreConfig coreConfig = new CoreConfig.Builder(this, new myImageLoader(), themeConfig)
                        .setFunctionConfig(functionConfig)
                        .build();
                //把所有配置与GalleryFinal进行关联
                GalleryFinal.init(coreConfig);
                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY,   new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        for (int i = 0; i < resultList.size(); i++) {
                            if (reqeustCode == REQUEST_CODE_GALLERY) {
                                Glide.with(getApplication()).load(resultList.get(i).getPhotoPath()).into(shoujiIvDi);
                                shoujiIvZhong.setVisibility(View.INVISIBLE);
                                shoujiIvXiao.setVisibility(View.INVISIBLE);
                                 photoPath = resultList.get(i).getPhotoPath();

                            }
                        }
                    }
                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {
                    }
                });
                break;
            case R.id.botton_ok:
                String pinpai = shoujiTvPinpai.getText().toString();
                String xinghao = shoujiTvXinghao.getText().toString();
                String leixing = "";
                String guzhang =shoujiTvGuzhang.getText().toString();
                String miaoshu = shoujiEtMiaoshu.getText().toString().trim();
                shouJi=new ShouJi();
                shouJi.setPINPAI(pinpai);
                shouJi.setGUZHANG(guzhang);
                shouJi.setXINGHAO(xinghao);
                shouJi.setLEIXING(xinghao);
                shouJi.setTUPIAN(photoPath);
                shouJi.setMIAOSHU(miaoshu);
                intent = new Intent(this,HuJiaoFuWu_Activity_Main.class);
                intent.putExtra("type","A");
                intent.putExtra("Bean", shouJi);
                startActivity(intent);
                break;
        }
    }


  public  static class myImageLoader implements ImageLoader {

        @Override
        public void displayImage(Activity activity, String path, GFImageView imageView, Drawable defaultDrawable, int width, int height) {
            //借助glide帮galleryfinal加载图片
            Glide.with(activity)
                    .load("file://" + path)                        //文件地址
                    .placeholder(defaultDrawable)                //加载中图片
                    .error(defaultDrawable)                        //错误图片
                    .override(width, height)                        //图片宽高
                    .diskCacheStrategy(DiskCacheStrategy.NONE)    //禁止磁盘缓存
                    .skipMemoryCache(true)                        //禁止内存缓存
                    .into(imageView);                            //加载图片到GalleryFinal
        }

        @Override
        public void clearMemoryCache() {//清除缓存不需要搭理

        }
    }

}
