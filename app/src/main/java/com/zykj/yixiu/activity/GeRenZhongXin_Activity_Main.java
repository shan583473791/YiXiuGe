package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by zykj on 2017/4/18.
 */

public class GeRenZhongXin_Activity_Main extends Activity {
    private final int REQUEST_CODE_CAMERA = 1000; //相机表示
    private final int REQUEST_CODE_GALLERY = 1001; //相册标示
    private final int REQUEST_CODE_CROP = 1002;    //裁剪表示
    private final int REQUEST_CODE_EDIT = 1003;       //编辑表示
    @Bind(R.id.gerenzhongxin_activity_fanhui)
    ImageView gerenzhongxinActivityFanhui;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.grzx_activity_weiwancheng)
    LinearLayout grzxActivityWeiwancheng;
    @Bind(R.id.grzx_activity_yiwancheng)
    LinearLayout grzxActivityYiwancheng;
    @Bind(R.id.grzx_activity_yiquxiao)
    LinearLayout grzxActivityYiquxiao;
    @Bind(R.id.grzx_activity_wodeziliao)
    LinearLayout grzxActivityWodeziliao;
    @Bind(R.id.grzx_activity_wodeqianbao)
    LinearLayout grzxActivityWodeqianbao;
    @Bind(R.id.grzx_activity_dizhiguanli)
    LinearLayout grzxActivityDizhiguanli;
    @Bind(R.id.grzx_activity_renzhengxinxi)
    LinearLayout grzxActivityRenzhengxinxi;
    @Bind(R.id.grzx_activity_pingtaifuwu)
    LinearLayout grzxActivityPingtaifuwu;
    @Bind(R.id.textView3)
    TextView textView3;
    @Bind(R.id.grzx_activity_guanyuwomen)
    LinearLayout grzxActivityGuanyuwomen;
    @Bind(R.id.grzx_activity_shezhi)
    LinearLayout grzxActivityShezhi;
    @Bind(R.id.gerenzhongxin_iv_touxiang)
    ImageView gerenzhongxinIvTouxiang;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenzhongxin);
        ButterKnife.bind(this);
        try {
            if (!TextUtils.isEmpty(Y.user.getIcon())){
                ImageOptions imageOptions=new ImageOptions.Builder()
                        .setCircular(true)
                        .build();
                x.image().bind(gerenzhongxinIvTouxiang,YURL.HOST+Y.user.getIcon(),imageOptions);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @OnClick({R.id.gerenzhongxin_activity_fanhui, R.id.grzx_activity_weiwancheng, R.id.grzx_activity_yiwancheng, R.id.grzx_activity_yiquxiao, R.id.grzx_activity_wodeziliao, R.id.grzx_activity_wodeqianbao, R.id.grzx_activity_dizhiguanli, R.id.grzx_activity_renzhengxinxi, R.id.grzx_activity_pingtaifuwu, R.id.grzx_activity_guanyuwomen, R.id.grzx_activity_shezhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.gerenzhongxin_activity_fanhui:
                finish();
                break;
            case R.id.grzx_activity_weiwancheng:
                break;
            case R.id.grzx_activity_yiwancheng:
                break;
            case R.id.grzx_activity_yiquxiao:
                break;
            case R.id.grzx_activity_wodeziliao:
                Intent ziliao=new Intent(GeRenZhongXin_Activity_Main.this,WoDeZiLiao_Activity_Main.class);
                startActivity(ziliao);
               /* intent = new Intent(this, WoDeZiLiao_Activity_Main.class);
                startActivity(intent);*/
                break;
            case R.id.grzx_activity_wodeqianbao:
                intent = new Intent(this, WoDeQianBao_Activity_Main.class);
                startActivity(intent);
                break;
            case R.id.grzx_activity_dizhiguanli:
                intent = new Intent(this, DiZhiGuanLi_Activity_Main.class);
                startActivity(intent);
                break;
            case R.id.grzx_activity_renzhengxinxi:
                intent = new Intent(this, RenZhengXinXi_Activity_Main.class);
                startActivity(intent);
                break;
            case R.id.grzx_activity_pingtaifuwu:
                intent = new Intent(this, PingTaiFuWu_Activity_Main.class);
                startActivity(intent);
                break;
            case R.id.grzx_activity_guanyuwomen:
                break;
            case R.id.grzx_activity_shezhi:
                break;
        }
    }

    @OnClick(R.id.gerenzhongxin_iv_touxiang)
    public void onViewClicked() {
        GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                ImageOptions imageOptions=new ImageOptions.Builder()
                        .setCircular(true)
                        .build();
                x.image().bind(gerenzhongxinIvTouxiang,resultList.get(0).getPhotoPath(),imageOptions);
                //Glide.with(getApplication()).load(resultList.get(0).getPhotoPath()).into(gerenzhongxinIvTouxiang);
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
}
