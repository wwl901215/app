package com.wwl.can;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

import com.wwl.can.canvas.CanvasMenu;
import com.wwl.can.chartroom.ui.LoginActivity;
import com.wwl.can.learn.Learn;
import com.wwl.can.location.map.BaiduBaseMapActivity;
import com.wwl.can.location.ui.LocationActiviey;
import com.wwl.can.pulltorefreshview.PullToRefreshViewActivity;
import com.wwl.can.utils.StatusBarUtils;
import com.wwl.can.webview.WebViewActivity;
import com.wwl.can.wifi.ui.WiFiActivity;
import com.wwl.can.zhujie.AnnotationActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.bt_wifi)
    Button btWifi;
    @Bind(R.id.bt_bluetooth)
    Button btBluetooth;
    @Bind(R.id.bt_learn)
    Button btLearn;
    @Bind(R.id.bt_chartroom)
    Button btChartroom;
    @Bind(R.id.bt_annotation)
    Button btAnnotation;
    @Bind(R.id.bt_canvas)
    Button btCanvas;
    @Bind(R.id.bt_webview)
    Button btWebview;
    @Bind(R.id.bt_globallist)
    Button btGloballist;
    @Bind(R.id.bt_pull)
    Button btPull;
    @Bind(R.id.bt_location)
    Button btLocation;
    @Bind(R.id.bt_map)
    Button btMap;

    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置该activity是否全屏
        StatusBarUtils.setTranslucentForImageView(this,0,null);
        ButterKnife.bind(this);
//        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//        locationManager.addTestProvider(mMockProviderNsame, false, true, false, false, true, true,
//                true, 0, 5);
//        locationManager.setTestProviderEnabled(mMockProviderName, true);
////        locationManager.requestLocationUpdates();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    setLocation();
//                }
//            }
//        }).start();
    }

    //    https://github.com/YiuChoi/FakeGps/tree/master/app/src/main/java/name/caiyao/fakegps/hook
//    https://www.jianshu.com/p/91e312faa6c3
//    http://blog.csdn.net/Aslanchen/article/details/43449765
//    http://blog.csdn.net/qq_23547831/article/details/52033726
//    https://www.cnblogs.com/gordon0918/p/6689883.html
//    http://blog.csdn.net/mrglaucusss/article/details/50963542
//    https://www.jianshu.com/p/796e94d8af31
    private String mMockProviderName = LocationManager.GPS_PROVIDER;

    public void setLocation() {
        Location location = new Location(mMockProviderName);
        location.setTime(System.currentTimeMillis());
        location.setLatitude(31.3029742);
        location.setLongitude(120.6097126);
        location.setAltitude(2.0f);
        location.setAccuracy(3.0f);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            location.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        }
        locationManager.setTestProviderLocation(mMockProviderName, location);

    }

    @OnClick({R.id.bt_wifi, R.id.bt_bluetooth, R.id.bt_learn, R.id.bt_chartroom, R.id.bt_annotation, R.id.bt_canvas, R.id.bt_webview, R.id.bt_globallist, R.id.bt_pull, R.id.bt_location, R.id.bt_map})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_wifi:
                Intent intent = new Intent(MainActivity.this, WiFiActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_bluetooth:
                break;
            case R.id.bt_learn:
                Intent intent1 = new Intent(MainActivity.this, Learn.class);
                startActivity(intent1);
                break;
            case R.id.bt_chartroom:
                Intent intent2 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent2);
                break;
            case R.id.bt_annotation:
                Intent intent3 = new Intent(MainActivity.this, AnnotationActivity.class);
                startActivity(intent3);
                break;
            case R.id.bt_canvas:
                Intent intent4 = new Intent(MainActivity.this, CanvasMenu.class);
                startActivity(intent4);
                break;
            case R.id.bt_webview:
                Intent intent5 = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(intent5);
                break;
            case R.id.bt_globallist:
                Intent intent6 = new Intent(MainActivity.this, GlobalListActivity.class);
                startActivity(intent6);
                break;

            case R.id.bt_pull:
                Intent intent7 = new Intent(MainActivity.this, PullToRefreshViewActivity.class);
                startActivity(intent7);
                break;

            case R.id.bt_location:
                Intent intent8 = new Intent(MainActivity.this, LocationActiviey.class);
                startActivity(intent8);
                break;
            case R.id.bt_map:
                Intent intent9 = new Intent(MainActivity.this, BaiduBaseMapActivity.class);
                startActivity(intent9);
                break;
        }
    }
}
