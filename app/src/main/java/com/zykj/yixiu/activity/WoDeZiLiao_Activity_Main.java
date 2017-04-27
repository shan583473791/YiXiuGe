package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.MyTopBar;
import com.zykj.yixiu.app.OptionsPicke;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by zykj on 2017/4/20.
 */

public class WoDeZiLiao_Activity_Main extends Activity {
    private final int REQUEST_CODE_CAMERA = 1000; //相机表示
    private final int REQUEST_CODE_GALLERY = 1001; //相册标示
    private final int REQUEST_CODE_CROP = 1002;    //裁剪表示
    private final int REQUEST_CODE_EDIT = 1003;       //编辑表示
    public String shi;
    public String sheng;
    public String sex="男";
    private MyTopBar titleFanhui;
    private ImageView wodeziliaoActivityIvTouxiang,iv_xiao;
    private  EditText wodeziliaoEtName,wodeziliaoEtShoujihao;
    private RadioButton rbNan,rbNv;
    private RadioGroup rb;
    private TextView wodeziliaoTvDiqu;
    private LinearLayout wodeziliaoActivityDiqu;
    private  Button bottonOk;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wodeziliao);
        titleFanhui= (MyTopBar) findViewById(R.id.title_fanhui);
        wodeziliaoActivityIvTouxiang= (ImageView) findViewById(R.id.wodeziliao_activity_iv_touxiang);
        iv_xiao= (ImageView) findViewById(R.id.iv_xiao);
        wodeziliaoEtName= (EditText) findViewById(R.id.wodeziliao_et_name);
        wodeziliaoEtShoujihao= (EditText) findViewById(R.id.wodeziliao_et_shoujihao);
        rbNan= (RadioButton) findViewById(R.id.rb_nan);
        rbNv= (RadioButton) findViewById(R.id.rb_nv);
        rb= (RadioGroup) findViewById(R.id.rb);
        wodeziliaoTvDiqu= (TextView) findViewById(R.id.wodeziliao_tv_diqu);
        wodeziliaoActivityDiqu= (LinearLayout) findViewById(R.id.wodeziliao_activity_diqu);
        bottonOk= (Button) findViewById(R.id.botton_ok);

        rb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.rb_nan:
                            sex="男";
                        break;
                    case R.id.rb_nv:
                        sex="女";
                        break;
                }
                Y.user.setSex(sex);
            }
        });
        if (!TextUtils.isEmpty(Y.user.getIcon())){
            ImageOptions imageOptions=new ImageOptions.Builder().setCircular(true).build();
            x.image().bind(wodeziliaoActivityIvTouxiang,YURL.HOST+Y.user.getIcon(),imageOptions);
            iv_xiao.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(Y.user.getPhone())){
            wodeziliaoEtShoujihao.setText(Y.user.getPhone());
        }
        if (!TextUtils.isEmpty(Y.user.getUsername())){
            wodeziliaoEtShoujihao.setText(Y.user.getUsername());
        }
        if (!TextUtils.isEmpty(Y.user.getSex())){
           switch (Y.user.getSex()){
               case "男":
                   rbNan.setChecked(true);
                   break;
               case "女":
                   rbNv.setChecked(true);
                   break;
           }
        }
        if (!TextUtils.isEmpty(Y.user.getProvince())||!TextUtils.isEmpty(Y.user.getCity())){
            wodeziliaoTvDiqu.setText(Y.user.getProvince()+Y.user.getCity());
        }
        wodeziliaoActivityIvTouxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        Glide.with(getApplication()).load(resultList.get(0).getPhotoPath()).into(wodeziliaoActivityIvTouxiang);
                        iv_xiao.setVisibility(View.GONE);
                        RequestParams params=new RequestParams(YURL.UPLOADICON);
                        File file = new File(resultList.get(0).getPhotoPath());
                        params.addBodyParameter("icon",file);
                        params.addBodyParameter("token", Y.TOKEN);
                        Y.postFile(params, new Y.MyCommonCall<String>() {
                            @Override
                            public void onSuccess(String result) {
                                StyledDialog.dismissLoading();
                                Y.user.setIcon( Y.getData(result));
                            }
                        });
                    }
                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {
                    }
                });
            }
        });
        wodeziliaoActivityDiqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionsPicke optionsPicke = new OptionsPicke();
                optionsPicke.showOptionsPicke(WoDeZiLiao_Activity_Main.this, new OptionsPicke.OptionsSelectListener() {
                    @Override
                    public void selectListener(String province, String city, String district) {
                        sheng = province.toString();
                        shi = city.toString();
                        wodeziliaoTvDiqu.setText(province+city);
                    }
                });
            }
        });
        bottonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams params=new RequestParams(YURL.UPLOADICON);
                final String name= wodeziliaoEtName.getText().toString().trim();
                params.addBodyParameter("username",name);
                params.addBodyParameter("token", Y.TOKEN);
                params.addBodyParameter("sex", Y.user.getSex());
                params.addBodyParameter("province", sheng);
                params.addBodyParameter("city", shi);
                params.addBodyParameter("user_id", Y.user.getUser_id()+"");
                Y.post(params, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        Y.t("上传成功");
                        Y.user.setUsername(name);
                        Y.user.setProvince(sheng);
                        Y.user.setProvince(shi);
                       finish();

                    }
                });
            }
        });
    }



}
