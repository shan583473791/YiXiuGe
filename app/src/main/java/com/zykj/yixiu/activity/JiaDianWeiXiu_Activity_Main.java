package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
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
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.MyDianNao;
import com.zykj.yixiu.app.MyDianNao_LeiXing;
import com.zykj.yixiu.app.MyTopBar;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import org.xutils.http.RequestParams;

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
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by zykj on 2017/4/18.
 */

public class JiaDianWeiXiu_Activity_Main extends Activity {
    private final int REQUEST_CODE_CAMERA = 1000; //相机表示
    private final int REQUEST_CODE_GALLERY = 1001; //相册标示
    private final int REQUEST_CODE_CROP = 1002;    //裁剪表示
    private final int REQUEST_CODE_EDIT = 1003;       //编辑表示
    @Bind(R.id.title_fanhui)
    MyTopBar titleFanhui;
    @Bind(R.id.jiadian_tv_pinpai)
    TextView jiadianTvPinpai;
    @Bind(R.id.jiadianweixiu_activity_ll_pinpai)
    LinearLayout jiadianweixiuActivityLlPinpai;
    @Bind(R.id.jiadian_tv_leixing)
    TextView jiadianTvLeixing;
    @Bind(R.id.jiadianweixiu_activity_ll_leixing)
    LinearLayout jiadianweixiuActivityLlLeixing;
    @Bind(R.id.jiadian_tv_xinghao)
    TextView jiadianTvXinghao;
    @Bind(R.id.jiadianweixiu_activity_ll_xinghao)
    LinearLayout jiadianweixiuActivityLlXinghao;
    @Bind(R.id.jiadian_tv_guzhang)
    TextView jiadianTvGuzhang;
    @Bind(R.id.jiadianweixiu_activity_ll_guzhang)
    LinearLayout jiadianweixiuActivityLlGuzhang;
    @Bind(R.id.editText2)
    EditText editText2;
    @Bind(R.id.jiadian_iv_di)
    ImageView jiadianIvDi;
    @Bind(R.id.jiadian_iv_zhong)
    ImageView jiadianIvZhong;
    @Bind(R.id.jiadian_iv_xiao)
    ImageView jiadianIvXiao;
    @Bind(R.id.jiadianweixiu_activity_fl_tu)
    FrameLayout jiadianweixiuActivityFlTu;
    @Bind(R.id.botton_ok)
    Button bottonOk;
    private ThemeConfig themeConfig;
    private OptionsPickerView opv;
    private Intent intent;
    List<MyDianNao_LeiXing> list; //品牌的数据源
    int mobileIndex = -1;  //用于检测是否选择了品牌
    List<MyDianNao> lists; //品牌的数据源
    private Map map;
    private int pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiadianweixiu);
        ButterKnife.bind(this);
        themeConfig = new ThemeConfig.Builder().build();
        titleFanhui.setLeftClick(new MyTopBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
    }

    @OnClick({R.id.jiadianweixiu_activity_ll_pinpai, R.id.jiadianweixiu_activity_ll_leixing, R.id.jiadianweixiu_activity_ll_xinghao, R.id.jiadianweixiu_activity_ll_guzhang, R.id.jiadianweixiu_activity_fl_tu, R.id.botton_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jiadianweixiu_activity_ll_pinpai:
                //发起请求
                Y.get(YURL.FIND_BY_APPLIANCE_BRAND,null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            //成功
                            list = JSON.parseArray(Y.getData(result), MyDianNao_LeiXing.class);
                            //创建选择器
                            //选择后的监听器
                            // 当前选择的索引
                            if (opv==null)
                                opv = new OptionsPickerView.Builder(JiaDianWeiXiu_Activity_Main.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        jiadianTvPinpai.setText(list.get(options1).getName());
                                        if ( mobileIndex != options1){


                                            pid = list.get(options1).getId();

                                            mobileIndex = options1; // 当前选择的索引
                                            //如果从选型号 把下面清空
                                            jiadianTvLeixing.setHint("请选择您的家电类型");
                                            jiadianTvLeixing.setText("");
                                            jiadianTvXinghao.setHint("请选择您家电的型号");
                                            jiadianTvXinghao.setText("");
                                            jiadianTvGuzhang.setHint("请选择您家电的故障");
                                            jiadianTvGuzhang.setText("");
                                        }
                                        mobileIndex = options1; // 当前选择的索引
                                    }
                                }).build();
                            //把lists 进行转换
                            List<String> strs = new ArrayList<String>();
                            for (MyDianNao_LeiXing mb : list) {
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
            case R.id.jiadianweixiu_activity_ll_leixing:
                //发起请求
                Map map=new HashMap();
                map.put("pid",pid);

                Y.get(YURL.FIND_APPLIANCE_CATEGORY, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if (Y.getRespCode(result)) {
                            StyledDialog.dismissLoading();
                            //成功
                            list = JSON.parseArray(Y.getData(result), MyDianNao_LeiXing.class);
                            //创建选择器
                            //选择后的监听器
                            // 当前选择的索引
                            if (opv==null)
                                opv = new OptionsPickerView.Builder(JiaDianWeiXiu_Activity_Main.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        jiadianTvLeixing.setText(list.get(options1).getName());
                                        if ( mobileIndex != options1){

                                            mobileIndex = options1; // 当前选择的索引
                                            //如果从选型号 把下面清空
                                            jiadianTvXinghao.setHint("请选择您家电的型号");
                                            jiadianTvXinghao.setText("");
                                            jiadianTvGuzhang.setHint("请选择您家电的故障");
                                            jiadianTvGuzhang.setText("");
                                        }
                                        mobileIndex = options1; // 当前选择的索引
                                    }
                                }).build();
                            //把lists 进行转换
                            List<String> strs = new ArrayList<String>();
                            for (MyDianNao_LeiXing mb : list) {
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
            case R.id.jiadianweixiu_activity_ll_xinghao:
                //发起请求
                map = new HashMap();
                map.put("pid",pid);
                map.put("category",list.get(mobileIndex).getId());
                Y.get(YURL.FIND_BY_APPLIANCE_MODEL,null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if (Y.getRespCode(result)) {
                            StyledDialog.dismissLoading();
                            //成功
                            list = JSON.parseArray(Y.getData(result), MyDianNao_LeiXing.class);
                            //创建选择器
                            //选择后的监听器
                            // 当前选择的索引
                            if (opv==null)
                                opv = new OptionsPickerView.Builder(JiaDianWeiXiu_Activity_Main.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        jiadianTvXinghao.setText(list.get(options1).getName());
                                        if ( mobileIndex != options1){

                                            mobileIndex = options1; // 当前选择的索引
                                            //如果从选型号 把下面清空
                                            jiadianTvGuzhang.setHint("请选择您家电的故障");
                                            jiadianTvGuzhang.setText("");
                                        }
                                        mobileIndex = options1; // 当前选择的索引
                                    }
                                }).build();
                            //把lists 进行转换
                            List<String> strs = new ArrayList<String>();
                            for (MyDianNao_LeiXing mb : list) {
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
            case R.id.jiadianweixiu_activity_ll_guzhang:
                //发起请求
                Y.get(YURL.FIND_PHONE_FAULT,null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if (Y.getRespCode(result)) {
                            //成功
                            lists = JSON.parseArray(Y.getData(result), MyDianNao.class);
                            //创建选择器
                            opv = new OptionsPickerView.Builder(JiaDianWeiXiu_Activity_Main.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                    //选择后的监听器
                                    jiadianTvGuzhang.setText(lists.get(options1).getName());
                                }
                            }).build();
                            //把lists 进行转换
                            List<String> strs = new ArrayList<String>();
                            for (MyDianNao mb : lists) {
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
            case R.id.jiadianweixiu_activity_fl_tu:

                FunctionConfig functionConfig = new FunctionConfig.Builder().build();
                CoreConfig coreConfig = new CoreConfig.Builder(this, new ShouJiWeiXiu_Activity_Main.myImageLoader(), themeConfig)
                        .setFunctionConfig(functionConfig)
                        .build();
                //把所有配置与GalleryFinal进行关联
                GalleryFinal.init(coreConfig);
                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        for (int i = 0; i < resultList.size(); i++) {
                            if (reqeustCode == REQUEST_CODE_GALLERY) {
                                Glide.with(getApplication()).load(resultList.get(i).getPhotoPath()).into(jiadianIvDi);
                                jiadianIvZhong.setVisibility(View.INVISIBLE);
                                jiadianIvXiao.setVisibility(View.INVISIBLE);
                            }
                        }
                    }
                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {
                    }
                });
                break;
            case R.id.botton_ok:
                intent = new Intent(this,HuJiaoFuWu_Activity_Main.class);
                startActivity(intent);
                break;
        }
    }
}
