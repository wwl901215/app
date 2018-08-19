package com.wwl.can.location.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.navisdk.adapter.BNRoutePlanNode;
import com.baidu.navisdk.adapter.BNRoutePlanNode.CoordinateType;
import com.baidu.navisdk.adapter.BaiduNaviManagerFactory;
import com.baidu.navisdk.adapter.IBNRoutePlanManager;
import com.baidu.navisdk.adapter.IBNTTSManager;
import com.baidu.navisdk.adapter.IBaiduNaviManager;
import com.wwl.can.R;
import com.wwl.can.location.ui.frg.NavigationFragment;
import com.wwl.can.location.ui.utils.LocationUtils;
import com.wwl.can.location.ui.utils.MyLocationListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LocationActiviey extends AppCompatActivity {

    @Bind(R.id.btn_BD09_MC)
    Button btnBD09MC;
    @Bind(R.id.btn_BD09LL)
    Button btnBD09LL;
    @Bind(R.id.btn_GCJ02)
    Button btnGCJ02;
    @Bind(R.id.btn_WGS84)
    Button btnWGS84;
    @Bind(R.id.baidu_fl)
    FrameLayout baiduFl;
    private String mSDCardPath = null;
    private static final String APP_FOLDER_NAME = "BNSDKSimpleDemo";
    private static final String[] authBaseArr = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final int authBaseRequestCode = 1;
    private boolean hasInitSuccess = false;
    private BNRoutePlanNode mStartNode = null;


    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_activiey);
        ButterKnife.bind(this);
        //百度定位相关配置；
        iniClinet();
        initOption();
        startListener();
        //百度导航初始化；
        initBaidu();
    }

    private void initView() {
        FragmentManager manager = getSupportFragmentManager();
        NavigationFragment fragment = new NavigationFragment();
        fragment.setContext(this);
        fragment.setStartNode(mStartNode);
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.baidu_fl,fragment);
        fragmentTransaction.commit();
    }

    private void startListener() {
        //mLocationClient为第二步初始化过的LocationClient对象
//调用LocationClient的start()方法，便可发起定位请求
        mLocationClient.start();
    }

    private void initOption() {
        LocationClientOption option = LocationUtils.setLocationClientOption();
        //mLocationClient为第二步初始化过的LocationClient对象
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
//更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
        mLocationClient.setLocOption(option);
    }

    private static double mLat = 0;
    private static double mLot = 0;
    private void iniClinet() {
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        myListener.setLin(new MyLocationListener.GetLL() {
            @Override
            public void getLL(double lat, double lot) {
//                Toast.makeText(getApplicationContext(), "lat:" + lat + " lot:" + lot, Toast.LENGTH_SHORT).show();
                mLat = lat;
                mLot = lot;
            }
        });
    }

    private void initBaidu() {

        if (initDirs()) {
            // 申请权限
            if (Build.VERSION.SDK_INT >= 23) {
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
                            hasInitSuccess = true;
//                            // 初始化tts 暂时没有获取tts id，无法使用语音播报；
                            initTTS();
                        }


                        @Override
                        public void initFailed() {
                            Toast.makeText(LocationActiviey.this, "百度导航引擎初始化失败", Toast.LENGTH_SHORT).show();

                        }
                    });
        }
    }

    private void initTTS() {
        // 使用内置TTS
        //TUDO
        BaiduNaviManagerFactory.getTTSManager().initTTS(getApplicationContext(),
                getSdcardDir(), APP_FOLDER_NAME, "tts id");

        // 不使用内置TTS
//         BaiduNaviManagerFactory.getTTSManager().initTTS(mTTSCallback);

        // 注册同步内置tts状态回调
        BaiduNaviManagerFactory.getTTSManager().setOnTTSStateChangedListener(
                new IBNTTSManager.IOnTTSPlayStateChangedListener() {
                    @Override
                    public void onPlayStart() {
                        Log.e("BNSDKDemo", "ttsCallback.onPlayStart");
                    }

                    @Override
                    public void onPlayEnd(String speechId) {
                        Log.e("BNSDKDemo", "ttsCallback.onPlayEnd");
                    }

                    @Override
                    public void onPlayError(int code, String message) {
                        Log.e("BNSDKDemo", "ttsCallback.onPlayError");
                    }
                }
        );

        // 注册内置tts 异步状态消息
        BaiduNaviManagerFactory.getTTSManager().setOnTTSStateChangedHandler(
                new Handler(Looper.getMainLooper()) {
                    @Override
                    public void handleMessage(Message msg) {
                        Log.e("BNSDKDemo", "ttsHandler.msg.what=" + msg.what);
                    }
                }
        );
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


    @OnClick({R.id.btn_BD09_MC, R.id.btn_BD09LL, R.id.btn_GCJ02, R.id.btn_WGS84})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_BD09_MC:
                this.routeplanToNavi(CoordinateType.BD09_MC);
                break;
            case R.id.btn_BD09LL:
                this.routeplanToNavi(CoordinateType.BD09LL);
                break;
            case R.id.btn_GCJ02:
                this.routeplanToNavi(CoordinateType.GCJ02);
                break;
            case R.id.btn_WGS84:
                this.routeplanToNavi(CoordinateType.BD09_MC);
                break;
        }
    }


    private void routeplanToNavi(final int coType) {
        if (!hasInitSuccess) {
            Toast.makeText(LocationActiviey.this, "还未初始化!", Toast.LENGTH_SHORT).show();
        }

        BNRoutePlanNode sNode = new BNRoutePlanNode(116.30142, 40.05087, "百度大厦", "百度大厦", coType);
        BNRoutePlanNode eNode = new BNRoutePlanNode(116.39750, 39.90882, "北京天安门", "北京天安门", coType);
        switch (coType) {
            case CoordinateType.GCJ02: {
                sNode = new BNRoutePlanNode(116.30142, 40.05087, "百度大厦", "百度大厦", coType);
                eNode = new BNRoutePlanNode(116.39750, 39.90882, "北京天安门", "北京天安门", coType);
                break;
            }
            case CoordinateType.WGS84: {
                sNode = new BNRoutePlanNode(116.300821, 40.050969, "百度大厦", "百度大厦", coType);
                eNode = new BNRoutePlanNode(116.397491, 39.908749, "北京天安门", "北京天安门", coType);
                break;
            }
            case CoordinateType.BD09_MC: {
                sNode = new BNRoutePlanNode(12947471, 4846474, "百度大厦", "百度大厦", coType);
                eNode = new BNRoutePlanNode(12958160, 4825947, "北京天安门", "北京天安门", coType);
                break;
            }
            case CoordinateType.BD09LL: {
                if (mLot != 0 && mLat != 0) {
                    sNode = new BNRoutePlanNode(mLot, mLat, "久创科技园", "久创科技园", coType);
                    eNode = new BNRoutePlanNode(121.444907,31.200107, "徐家汇", "徐家汇地铁站", coType);
                }else {
                    sNode = new BNRoutePlanNode(121.407181, 31., "百度大厦", "百度大厦", coType);
                    eNode = new BNRoutePlanNode(116.40386525193937, 39.915160800132085, "北京天安门", "北京天安门", coType);
                }
                break;
            }
            default:
                break;
        }

        mStartNode = sNode;

        List<BNRoutePlanNode> list = new ArrayList<BNRoutePlanNode>();
        list.add(sNode);
        list.add(eNode);

        BaiduNaviManagerFactory.getRoutePlanManager().routeplanToNavi(
                list,
                IBNRoutePlanManager.RoutePlanPreference.ROUTE_PLAN_PREFERENCE_DEFAULT,
                null,
                new Handler(Looper.getMainLooper()) {
                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case IBNRoutePlanManager.MSG_NAVI_ROUTE_PLAN_START:
                                Toast.makeText(LocationActiviey.this, "算路开始", Toast.LENGTH_SHORT)
                                        .show();
                                break;
                            case IBNRoutePlanManager.MSG_NAVI_ROUTE_PLAN_SUCCESS:
                                Toast.makeText(LocationActiviey.this, "算路成功", Toast.LENGTH_SHORT)
                                        .show();
                                break;
                            case IBNRoutePlanManager.MSG_NAVI_ROUTE_PLAN_FAILED:
                                Toast.makeText(LocationActiviey.this, "算路失败", Toast.LENGTH_SHORT)
                                        .show();
                                break;
                            case IBNRoutePlanManager.MSG_NAVI_ROUTE_PLAN_TO_NAVI:
                                Toast.makeText(LocationActiviey.this, "算路成功准备进入导航", Toast.LENGTH_SHORT)
                                        .show();
                                initView();
                                break;
                            default:
                                // nothing
                                break;
                        }
                    }
                });
    }
}
