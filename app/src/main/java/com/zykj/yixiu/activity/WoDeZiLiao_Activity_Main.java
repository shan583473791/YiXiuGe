package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

    @Bind(R.id.title_fanhui)
    MyTopBar titleFanhui;
    @Bind(R.id.wodeziliao_activity_iv_touxiang)
    ImageView wodeziliaoActivityIvTouxiang;
    @Bind(R.id.wodeziliao_et_name)
    EditText wodeziliaoEtName;
    @Bind(R.id.rb_nan)
    RadioButton rbNan;
    @Bind(R.id.rb_nv)
    RadioButton rbNv;
    @Bind(R.id.rb)
    RadioGroup rb;
    @Bind(R.id.wodeziliao_et_shoujihao)
    EditText wodeziliaoEtShoujihao;
    @Bind(R.id.wodeziliao_tv_diqu)
    TextView wodeziliaoTvDiqu;
    @Bind(R.id.wodeziliao_activity_diqu)
    LinearLayout wodeziliaoActivityDiqu;
    @Bind(R.id.botton_ok)
    Button bottonOk;
    public String shi;
    public String sheng;
    public String sex="男";
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wodeziliao);
        ButterKnife.bind(this);
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
    }

    @OnClick({R.id.wodeziliao_activity_iv_touxiang, R.id.wodeziliao_activity_diqu, R.id.botton_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wodeziliao_activity_iv_touxiang:
                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        Glide.with(getApplication()).load(resultList.get(0).getPhotoPath()).into(wodeziliaoActivityIvTouxiang);
                        RequestParams params=new RequestParams(YURL.UPLOADICON);
                        params.addBodyParameter("icon",resultList.get(0).getPhotoPath());
                        params.addBodyParameter("token", Y.TOKEN);
                        Y.post(params, new Y.MyCommonCall<String>() {
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
                break;
            case R.id.wodeziliao_activity_diqu:
                OptionsPicke optionsPicke = new OptionsPicke();
                optionsPicke.showOptionsPicke(this, new OptionsPicke.OptionsSelectListener() {
                    @Override
                    public void selectListener(String province, String city, String district) {
                        sheng = province.toString();
                        shi = city.toString();
                        wodeziliaoTvDiqu.setText(province+city);
                    }
                });
                break;
            case R.id.botton_ok:

                RequestParams params=new RequestParams(YURL.UPLOADICON);
                params.addBodyParameter("username",wodeziliaoEtName.getText().toString().trim());
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
                        startActivity(new Intent(WoDeZiLiao_Activity_Main.this,GeRenZhongXin_Activity_Main.class));
                    }
                });
                break;
        }
    }
}
