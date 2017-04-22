package com.zykj.yixiu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.MyTopBar;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import org.xutils.http.RequestParams;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by zykj on 2017/4/20.
 */

public class RenZhengXinXi_Activity_Main extends Activity {
    private final int REQUEST_CODE_CAMERA = 1000; //相机表示
    private final int REQUEST_CODE_GALLERY = 1001; //相册标示
    private final int REQUEST_CODE_CROP = 1002;    //裁剪表示
    private final int REQUEST_CODE_EDIT = 1003;       //编辑表示
    @Bind(R.id.title_fanhui)
    MyTopBar titleFanhui;
    @Bind(R.id.zheng_iv_di)
    ImageView zhengIvDi;
    @Bind(R.id.zheng_iv_zhong)
    ImageView zhengIvZhong;
    @Bind(R.id.zheng_iv_xiao)
    ImageView zhengIvXiao;
    @Bind(R.id.renzhengxinxi_ll_zheng)
    LinearLayout renzhengxinxiLlZheng;
    @Bind(R.id.fan_iv_di)
    ImageView fanIvDi;
    @Bind(R.id.fan_iv_zhong)
    ImageView fanIvZhong;
    @Bind(R.id.fan_iv_xiao)
    ImageView fanIvXiao;
    @Bind(R.id.renzhengxinxi_ll_fan)
    LinearLayout renzhengxinxiLlFan;
    @Bind(R.id.renzhengxinxi_by_ok)
    Button renzhengxinxiByOk;
    private String zhengtu;
    private String fantu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renzhengxinxi);
        ButterKnife.bind(this);
        titleFanhui.setLeftClick(new MyTopBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
    }

    @OnClick({R.id.renzhengxinxi_ll_zheng, R.id.renzhengxinxi_ll_fan, R.id.renzhengxinxi_by_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.renzhengxinxi_ll_zheng:
                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, new GalleryFinal.OnHanlderResultCallback() {



                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        zhengtu = resultList.get(0).getPhotoPath();
                        Glide.with(getApplication()).load(zhengtu).into(zhengIvDi);
                        zhengIvZhong.setVisibility(View.GONE);
                        zhengIvXiao.setVisibility(View.GONE);

                    }
                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {
                    }
                });
                break;
            case R.id.renzhengxinxi_ll_fan:
                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        fantu = resultList.get(0).getPhotoPath();
                        Glide.with(getApplication()).load(fantu).into(fanIvDi);
                        fanIvZhong.setVisibility(View.GONE);
                        fanIvXiao.setVisibility(View.GONE);

                    }
                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {
                    }
                });
                break;
            case R.id.renzhengxinxi_by_ok:
                RequestParams params=new RequestParams(YURL.UPLOADICON);
                params.addBodyParameter("idcard_image1",zhengtu);
                params.addBodyParameter("idcard_image2",fantu);
                params.addBodyParameter("token", Y.TOKEN);
                Y.post(params, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {

                        StyledDialog.dismissLoading();
                        ShenFenZheng shenFenZheng = JSON.parseObject(result, ShenFenZheng.class);
                        String zheng = shenFenZheng.getIdcard_image1();
                        String fan = shenFenZheng.getIdcard_image2();
                        Y.user.setIdcard_image1(zheng);
                        Y.user.setIdcard_image2(fan);
                    }
                });

                break;
        }
    }
    class ShenFenZheng{

        /**
         * idcard_image1 : idcard/bac82340-05e2-4307-a932-ab2382b46ea4.jpg
         * idcard_image2 : idcard/3f925c3c-9c3c-4dec-ac4c-8ae4b19347ed.jpg
         */

        private String idcard_image1;
        private String idcard_image2;

        public String getIdcard_image1() {
            return idcard_image1;
        }

        public void setIdcard_image1(String idcard_image1) {
            this.idcard_image1 = idcard_image1;
        }

        public String getIdcard_image2() {
            return idcard_image2;
        }

        public void setIdcard_image2(String idcard_image2) {
            this.idcard_image2 = idcard_image2;
        }
    }
}
