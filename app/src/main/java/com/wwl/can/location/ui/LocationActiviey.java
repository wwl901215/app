package com.wwl.can.location.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.navisdk.adapter.BaiduNaviManagerFactory;
import com.baidu.navisdk.adapter.IBaiduNaviManager;
import com.wwl.can.R;
import com.wwl.can.location.ui.utils.MyLocationListener;

import java.io.File;


public class LocationActiviey extends AppCompatActivity{

    private String mSDCardPath = null;
    private static final String APP_FOLDER_NAME = "BNSDKSimpleDemo";
    private static final String[] authBaseArr = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final int authBaseRequestCode = 1;


    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_activiey);
        iniClinet();
        initOption();
        startListener();


        initBaidu();
    }

    private void startListener() {
        mLocationClient.start();
//mLocationClient为第二步初始化过的LocationClient对象
//调用LocationClient的start()方法，便可发起定位请求
    }

    private void initOption() {
        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//可选，设置定位模式，默认高精度
//LocationMode.Hight_Accuracy：高精度；
//LocationMode. Battery_Saving：低功耗；
//LocationMode. Device_Sensors：仅使用设备；

        option.setCoorType("bd09ll");
//可选，设置返回经纬度坐标类型，默认gcj02
//gcj02：国测局坐标；
//bd09ll：百度经纬度坐标；
//bd09：百度墨卡托坐标；
//海外地区定位，无需设置坐标类型，统一返回wgs84类型坐标

        option.setScanSpan(1000);
//可选，设置发起定位请求的间隔，int类型，单位ms
//如果设置为0，则代表单次定位，即仅定位一次，默认为0
//如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
//可选，设置是否使用gps，默认false
//使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(true);
//可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(false);
//可选，定位SDK内部是一个service，并放到了独立进程。
//设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        option.SetIgnoreCacheException(false);
//可选，设置是否收集Crash信息，默认收集，即参数为false

        option.setWifiCacheTimeOut(5*60*1000);
//可选，7.2版本新增能力
//如果设置了该接口，首次启动定位时，会先判断当前WiFi是否超出有效期，若超出有效期，会先重新扫描WiFi，然后定位

        option.setEnableSimulateGps(false);
//可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false

        mLocationClient.setLocOption(option);
//mLocationClient为第二步初始化过的LocationClient对象
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
//更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明

    }

    private void iniClinet() {
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        myListener.setLin(new MyLocationListener.GetLL() {
            @Override
            public void getLL(double lat, double lot) {
                Toast.makeText(getApplicationContext(),"lat:" + lat + " lot:" + lot,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initBaidu() {

        if (initDirs()) {
            // 申请权限
            if (android.os.Build.VERSION.SDK_INT >= 23) {
                if (!hasBasePhoneAuth()) {
                    this.requestPermissions(authBaseArr, authBaseRequestCode);
                    return;
                }
            }
            BaiduNaviManagerFactory.getBaiduNaviManager().init(this, mSDCardPath, APP_FOLDER_NAME,
                    new IBaiduNaviManager.INaviInitListener() {
                        @Override
                        public void onAuthResult(int i, String s) {
                            String result;
                            if (0 == i) {
                                result = "key校验成功!";
                            } else {
                                result = "key校验失败, " + s;
                            }
                            Toast.makeText(LocationActiviey.this, result, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void initStart() {
                            Toast.makeText(LocationActiviey.this, "百度导航引擎初始化开始", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void initSuccess() {
                            Toast.makeText(LocationActiviey.this, "百度导航引擎初始化成功", Toast.LENGTH_SHORT).show();
//                            hasInitSuccess = true;
//                            // 初始化tts
//                            initTTS();
                        }


                        @Override
                        public void initFailed() {
                            Toast.makeText(LocationActiviey.this, "百度导航引擎初始化失败", Toast.LENGTH_SHORT).show();

                        }
                    });
        }
    }

    private boolean initDirs() {
        mSDCardPath = getSdcardDir();
        if (mSDCardPath == null) {
            return false;
        }
        File f = new File(mSDCardPath, APP_FOLDER_NAME);
        if (!f.exists()) {
            try {
                f.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    private boolean hasBasePhoneAuth() {
        PackageManager pm = this.getPackageManager();
        for (String auth : authBaseArr) {
            if (pm.checkPermission(auth, this.getPackageName()) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
    private String getSdcardDir() {
        if (Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().toString();
        }
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myListener != null) {
            mLocationClient.unRegisterLocationListener(myListener);
            mLocationClient.stop();
            mLocationClient = null;
        }
    }


}
