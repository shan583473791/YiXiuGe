package com.zykj.yixiu.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.zykj.yixiu.R;
import com.zykj.yixiu.utils.Y;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

class BaiDuDiTu extends AppCompatActivity implements View.OnClickListener {
    //百度地图视图

    @Bind(R.id.baiduditu_et_dizhi)
    EditText etDz;

    @Bind(R.id.bt_queren)
    Button btQueren;
    @Bind(R.id.bmapView)
    MapView bmapView;
    //百度地图管理器
    private BaiduMap baiduMap;
    LocationClient mClient;//客户端对象
    boolean isFirstLoc = true;//检测是否是第一次定位
    private LatLng latLng;
    private String string;
    private String district;
    private double jing;
    private double wei;
    private LatLng location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_baiduditu);
        ButterKnife.bind(this);
        //设置缩放级别，默认级别为12
        MapStatusUpdate mapstatusUpdate = MapStatusUpdateFactory.zoomTo(20);
        baiduMap = bmapView.getMap();
        initLocation();
        selectMap();
    }

    /**
     * 地图选点函数
     */
    public void selectMap() {
        baiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                jieMa(latLng);//反地理
                biaoZhu(latLng);//添加标注
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {

                return false;
            }
        });
    }

    /**
     * 添加标注函数
     *
     * @param latLng
     */
    public void biaoZhu(LatLng latLng) {
        baiduMap.clear();
        OverlayOptions overlayOptions = new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.zb));
        baiduMap.addOverlay(overlayOptions);

    }

    /**
     * button的点击事件
     *
     * @param v
     */
    @OnClick(R.id.bt_queren)
    public void onClick(View v) {
        //获取信息
        string = etDz.getText().toString();
        if (TextUtils.isEmpty(string)) {
            Toast.makeText(this, "请输入地址", Toast.LENGTH_SHORT).show();
            return;
        } else {
            //发起地理编码
            GeoCoder geoCoder = GeoCoder.newInstance();
            geoCoder.geocode(new GeoCodeOption().city(Y.user.getCity()).address(string));
            //设置地理编码的监听事件
            geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
                @Override
                public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                    //判断数据是否为空或者是否合法
                    if (geoCodeResult == null || geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                        Toast.makeText(BaiDuDiTu.this, "请输入合法地址", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(BaiDuDiTu.this, "成功", Toast.LENGTH_SHORT).show();
                        biaoZhu(geoCodeResult.getLocation());
                        MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(geoCodeResult.getLocation());//更新百度地图对象
                        baiduMap.animateMapStatus(update);//把更新的位置交给百度
                        Intent intent =new Intent();
                        intent.putExtra("dizhibianma",location);
                        intent.putExtra("dizhi",string);
                        intent.putExtra("qu",district);
                        intent.putExtra("jing",jing);
                        intent.putExtra("wei",wei);
                        setResult(101,intent);
                        finish();
                    }
                }

                @Override
                public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                }
            });


        }
    }

    /**
     * 地理编码函数
     *
     * @param latLng
     */
    public void jieMa(LatLng latLng) {
        GeoCoder geoCoder = GeoCoder.newInstance();
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                Toast.makeText(BaiDuDiTu.this, reverseGeoCodeResult.getAddress(), Toast.LENGTH_SHORT).show();
                etDz.setText(reverseGeoCodeResult.getAddress());
            }
        });
    }

    /**
     * 此函数用于实现定位功能
     */
    public void initLocation() {
        mClient = new LocationClient(this);//创建定位对象
        //使用 LocationClientOption 配置定位的所有信息
        LocationClientOption locationClientOption = new LocationClientOption();
        locationClientOption.setCoorType("bd09ll");//设置经纬度为百度的经纬度
        locationClientOption.setOpenGps(true);//设置GPS为开启状态
        locationClientOption.setScanSpan(5000);//设置定位间隔的时间
        locationClientOption.setIsNeedAddress(true);//设置定位成功后返回位置信息
        mClient.setLocOption(locationClientOption);//把配置好的LocationClientOption交给mClient
        //注册一个mClient的监听事件
        mClient.registerLocationListener(new BDLocationListener() {
            @Override//定位成功后返回的函数
            public void onReceiveLocation(final BDLocation bdLocation) {
                //判断是否为空
                if (bdLocation == null || baiduMap == null) {
                    Toast.makeText(BaiDuDiTu.this, "定位失败", Toast.LENGTH_SHORT).show();
                    return;
                }
                //成功并吐司位置

                //获取BDLocation数据 转换成MyLocationData
                MyLocationData myLocationData = new MyLocationData.Builder()
                        .accuracy(bdLocation.getRadius())//半径
                        .latitude(bdLocation.getLatitude())//经纬度
                        .longitude(bdLocation.getLatitude())//经纬度
                        .build();
                baiduMap.setMyLocationData(myLocationData);//把定位交给百度地图
                //更新地图位置
                latLng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
                if (isFirstLoc) {//判断是否是第一次定位
                    centerLocation();
                    Toast.makeText(BaiDuDiTu.this, bdLocation.getAddrStr(), Toast.LENGTH_SHORT).show();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            etDz.setText(bdLocation.getAddrStr());

                        }
                    });
                    Y.user.setCity(bdLocation.getCity());
                    district = bdLocation.getDistrict();
                    jing = bdLocation.getLatitude();
                    wei = bdLocation.getLongitude();
                    isFirstLoc = false;//改成false 已经不是第一次定位了
                }

                //绘制一个标注
                OverlayOptions overlayOptions = new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.zb))
                        .position(latLng);
                baiduMap.addOverlay(overlayOptions);
            }

            @Override//返回兴趣点信息
            public void onConnectHotSpotMessage(String s, int i) {
            }
        });
    }

    /**
     * 此函数用于实现百度坐标 回到我的位置
     */

    public void centerLocation() {
        MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(latLng);//更新百度地图对象
        baiduMap.animateMapStatus(update);//把更新的位置交给百度

    }

    @Override//启动
    protected void onStart() {
        super.onStart();
        baiduMap.setMyLocationEnabled(true);
        if (!mClient.isStarted()) {
            mClient.start();
        }
    }

    @Override//停止
    protected void onStop() {
        super.onStop();
        mClient.stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("定位我的位置");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.toString()) {
            case "定位我的位置":
                centerLocation();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bmapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bmapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bmapView.onPause();
    }
}
