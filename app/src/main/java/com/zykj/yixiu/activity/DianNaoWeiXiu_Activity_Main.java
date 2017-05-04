package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.zykj.yixiu.utils.ShouJi;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

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

public class DianNaoWeiXiu_Activity_Main extends Activity {
    private final int REQUEST_CODE_CAMERA = 1000; //相机表示
    private final int REQUEST_CODE_GALLERY = 1001; //相册标示
    private final int REQUEST_CODE_CROP = 1002;    //裁剪表示
    private final int REQUEST_CODE_EDIT = 1003;       //编辑表示

    @Bind(R.id.title_fanhui)
    MyTopBar titleFanhui;
    @Bind(R.id.diannao_tv_pinpai)
    TextView diannaoTvPinpai;
    @Bind(R.id.diannaoweixiu_activity_ll_pinpai)
    LinearLayout diannaoweixiuActivityLlPinpai;
    @Bind(R.id.diannao_tv_leixing)
    TextView diannaoTvLeixing;
    @Bind(R.id.diannaoweixiu_activity_ll_leixing)
    LinearLayout diannaoweixiuActivityLlLeixing;
    @Bind(R.id.diannaoweixiu_activity_ll_xinghao)
    LinearLayout diannaoweixiuActivityLlXinghao;
    @Bind(R.id.diannao_tv_xinghao)
    TextView diannaoTvXinghao;
    @Bind(R.id.diannao_tv_guzhang)
    TextView diannaoTvGuzhang;
    @Bind(R.id.diannaoweixiu_activity_ll_guzhang)
    LinearLayout diannaoweixiuActivityLlGuzhang;
    @Bind(R.id.diannao_iv_di)
    ImageView diannaoIvDi;
    @Bind(R.id.diannao_iv_zhong)
    ImageView diannaoIvZhong;
    @Bind(R.id.diannao_iv_xiao)
    ImageView diannaoIvXiao;
    @Bind(R.id.diannaoweixiu_activity_fl_tu)
    FrameLayout diannaoweixiuActivityFlTu;
    @Bind(R.id.botton_ok)
    Button bottonOk;
    @Bind(R.id.diannao_et_miaoshu)
    EditText diannaoEtMiaoshu;
    private ThemeConfig themeConfig;
    private OptionsPickerView opv;
    private Intent intent;
    List<MyDianNao> lists; //品牌的数据源
    List<MyDianNao_LeiXing> list; //品牌的数据源
    int mobileIndex = -1;  //用于检测是否选择了品牌
    private Map map;
    private ShouJi shouJi;
    private int id;
    private String photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diannaoweixiu);
        ButterKnife.bind(this);
        themeConfig = new ThemeConfig.Builder().build();
        titleFanhui.setLeftClick(new MyTopBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();//返回
            }
        });
    }

    @OnClick({R.id.diannaoweixiu_activity_ll_pinpai, R.id.diannaoweixiu_activity_ll_leixing, R.id.diannaoweixiu_activity_ll_xinghao, R.id.diannaoweixiu_activity_ll_guzhang, R.id.diannaoweixiu_activity_fl_tu, R.id.botton_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.diannaoweixiu_activity_ll_pinpai://选择品牌
                //发起请求
                Y.get(YURL.FIND_COMPUTER_BRAND, null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if (Y.getRespCode(result)) {
                            StyledDialog.dismissLoading();
                            //成功
                            lists = JSON.parseArray(Y.getData(result), MyDianNao.class);
                            //创建选择器
                            //选择后的监听器
                            // 当前选择的索引
                            if (opv == null)
                                opv = new OptionsPickerView.Builder(DianNaoWeiXiu_Activity_Main.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        diannaoTvPinpai.setText(lists.get(options1).getName());
                                        if (mobileIndex != options1) {

                                            mobileIndex = options1; // 当前选择的索引
                                            //如果从选型号 把下面清空
                                            diannaoTvLeixing.setHint("请选择您的电脑类型");
                                            diannaoTvLeixing.setText("");
                                            diannaoTvXinghao.setHint("请选择您电脑的型号");
                                            diannaoTvXinghao.setText("");
                                            diannaoTvGuzhang.setHint("请选择您电脑的故障");
                                            diannaoTvGuzhang.setText("");
                                        }
                                        mobileIndex = options1; // 当前选择的索引
                                        opv=null;
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
            case R.id.diannaoweixiu_activity_ll_leixing:
                //品牌下的类型
                //发起请求
                Map map = new HashMap();
                map.put("pid", lists.get(mobileIndex).getId()+"");
                Y.get(YURL.FIND_COMPUTER_CATEGORY, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if (Y.getRespCode(result)) {
                            StyledDialog.dismissLoading();
                            //成功
                            list = JSON.parseArray(Y.getData(result), MyDianNao_LeiXing.class);
                            //创建选择器
                            //选择后的监听器
                            // 当前选择的索引
                            if (opv == null)
                                opv = new OptionsPickerView.Builder(DianNaoWeiXiu_Activity_Main.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        diannaoTvLeixing.setText(list.get(options1).getName().toString());
                                        if (mobileIndex != options1) {

                                            mobileIndex = options1; // 当前选择的索引
                                            //如果从选型号 把下面清空
                                            diannaoTvXinghao.setHint("请选择您电脑的型号");
                                            diannaoTvXinghao.setText("");
                                            diannaoTvGuzhang.setHint("请选择您电脑的故障");
                                            diannaoTvGuzhang.setText("");
                                        }
                                        mobileIndex = options1; // 当前选择的索引
                                        opv=null;
                                    }
                                }).build();
                            //把lists 进行转换
                            List<String> strs = new ArrayList<String>();
                            for (MyDianNao_LeiXing b : list) {
                                strs.add(b.getName());
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
            case R.id.diannaoweixiu_activity_ll_xinghao:
                //类型下的型号
                //发起请求
                map = new HashMap();
                map.put("pid", lists.get(mobileIndex).getId()+"");
                map.put("category",list.get(mobileIndex).getId()+"");

                Y.get(YURL.FIND_BY_COMPUTER_MODEL, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if (Y.getRespCode(result)) {
                            StyledDialog.dismissLoading();
                            //成功
                            list = JSON.parseArray(Y.getData(result), MyDianNao_LeiXing.class);
                            //创建选择器
                            //选择后的监听器
                            // 当前选择的索引
                            if (opv == null)
                                opv = new OptionsPickerView.Builder(DianNaoWeiXiu_Activity_Main.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        diannaoTvXinghao.setText(list.get(options1).getName());
                                        if (mobileIndex != options1) {

                                            mobileIndex = options1; // 当前选择的索引
                                            //如果从选型号 把下面清空
                                            diannaoTvGuzhang.setHint("请选择您电脑的故障");
                                            diannaoTvGuzhang.setText("");
                                        }
                                        mobileIndex = options1; // 当前选择的索引
                                        opv=null;
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
            case R.id.diannaoweixiu_activity_ll_guzhang:
                //发起请求
                Y.get(YURL.FIND_PHONE_FAULT, null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if (Y.getRespCode(result)) {
                            StyledDialog.dismissLoading();
                            //成功
                            lists = JSON.parseArray(Y.getData(result), MyDianNao.class);
                            //创建选择器
                            opv = new OptionsPickerView.Builder(DianNaoWeiXiu_Activity_Main.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                    //选择后的监听器
                                    diannaoTvGuzhang.setText(lists.get(options1).getName());
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
            case R.id.diannaoweixiu_activity_fl_tu:
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
                                photoPath = resultList.get(i).getPhotoPath();
                                Glide.with(getApplication()).load(resultList.get(i).getPhotoPath()).into(diannaoIvDi);
                                diannaoIvZhong.setVisibility(View.INVISIBLE);
                                diannaoIvXiao.setVisibility(View.INVISIBLE);
                            }
                        }
                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {
                    }
                });
                break;
            case R.id.botton_ok:
                String pinpai = diannaoTvPinpai.getText().toString();
                String xinghao = diannaoTvXinghao.getText().toString();
                String leixing = diannaoTvLeixing.getText().toString();
                String guzhang = diannaoTvXinghao.getText().toString();
                String miaoshu = diannaoEtMiaoshu.getText().toString().trim();
                shouJi=new ShouJi();
                shouJi.setPINPAI(pinpai);
                shouJi.setGUZHANG(guzhang);
                shouJi.setXINGHAO(xinghao);
                shouJi.setLEIXING(xinghao);
                shouJi.setTUPIAN(photoPath);
                shouJi.setMIAOSHU(miaoshu);
                if (!TextUtils.isEmpty(diannaoTvPinpai.getText())&&!TextUtils.isEmpty(diannaoTvXinghao.getText())&&!TextUtils.isEmpty(diannaoTvLeixing.getText())&&!TextUtils.isEmpty(diannaoTvXinghao.getText())){
                    intent = new Intent(this, HuJiaoFuWu_Activity_Main.class);
                    intent.putExtra("type","C");
                    intent.putExtra("Bean", shouJi);
                    startActivity(intent);
                }

                break;
        }
    }
}
