package com.wwl.can.location.map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.TaxiInfo;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.SuggestAddrInfo;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.wwl.can.R;

import java.util.List;

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
    private RoutePlanSearch routePlanSearch = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu_base_map);
        ButterKnife.bind(this);

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        map = mMapView.getMap();
        searchPlanInit();
    }

    private void searchPlanInit() {
        OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {

            @Override
            public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

            }

            @Override
            public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

            }

            @Override
            public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

            }

            public void onGetDrivingRouteResult(DrivingRouteResult result) {
                //获取驾车线路规划结果
                List<DrivingRouteLine> routeLines = result.getRouteLines();
                for (DrivingRouteLine lin : routeLines) {
                    int linsNum = 0;
                    int lightNum = lin.getLightNum();
                    List<DrivingRouteLine.DrivingStep> allStep = lin.getAllStep();
                    Log.e("baidu-lightNum:",lightNum + "");
                    for (DrivingRouteLine.DrivingStep step: allStep) {
                        String title = step.getEntrance().getLocation().latitude + "";
                        Log.e("baidu-lati:","=========================拐点==============");
                        List<LatLng> wayPoints = step.getWayPoints();
                        if (wayPoints != null) {
                            for (LatLng ll: wayPoints) {
                                linsNum += 1;
                                Log.e("baidu-ll:", linsNum + "===" + ll.latitude + "");
                            }
                        }
                    }
                }
                SuggestAddrInfo suggestAddrInfo = result.getSuggestAddrInfo();
                List<TaxiInfo> taxiInfos = result.getTaxiInfos();
            }

            @Override
            public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

            }

            @Override
            public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

            }
        };
        routePlanSearch = RoutePlanSearch.newInstance();
        routePlanSearch.setOnGetRoutePlanResultListener(listener);


        PlanNode stNode = PlanNode.withCityNameAndPlaceName("北京", "龙泽");

        PlanNode enNode = PlanNode.withCityNameAndPlaceName("北京", "西单");

        routePlanSearch.drivingSearch((new DrivingRoutePlanOption())

                .from(stNode)
                .to(enNode));
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
        routePlanSearch.destroy();
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
