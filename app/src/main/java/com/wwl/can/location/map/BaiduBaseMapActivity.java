package com.wwl.can.location.map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.wwl.can.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaiduBaseMapActivity extends AppCompatActivity {
    @Bind(R.id.nomal_map)
    Button nomalMap;
    @Bind(R.id.satellite_map)
    Button satelliteMap;
    @Bind(R.id.none_map)
    Button noneMap;
    @Bind(R.id.bmapView)
    MapView bmapView;
    private MapView mMapView = null;
    private BaiduMap map = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu_base_map);
        ButterKnife.bind(this);

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        map = mMapView.getMap();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @OnClick({R.id.nomal_map, R.id.satellite_map, R.id.none_map})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.nomal_map:
                if (map != null){
                    map.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                }
                break;
            case R.id.satellite_map:
                if (map != null){
                    map.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                }
                break;
            case R.id.none_map:
                if (map != null){
                    map.setMapType(BaiduMap.MAP_TYPE_NONE);
                }
                break;
        }
    }
}
