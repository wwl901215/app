package com.wwl.can.location.ui.frg;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.navisdk.adapter.BNRouteGuideManager;
import com.baidu.navisdk.adapter.BNRoutePlanNode;
import com.baidu.navisdk.adapter.BNRoutePlanNode.CoordinateType;
import com.baidu.navisdk.adapter.BaiduNaviManagerFactory;
import com.baidu.navisdk.adapter.IBNRouteGuideManager;
import com.baidu.navisdk.adapter.map.BNItemizedOverlay;
import com.baidu.navisdk.adapter.map.BNOverlayItem;

import com.baidu.navisdk.adapter.IBNRouteGuideManager;
import com.wwl.can.location.ui.LocationActiviey;
import com.wwl.can.location.ui.utils.EventHandler;

/**
 * @version V1.0
 * @Created by: 艾克斯 .
 * @Date: 2018-08-12
 * @Package: com.wwl.can.location.ui.frg
 * @Description: ${TODO}
 * @author: 王文亮
 * @邮箱： wwl901215@163.com
 */

public class NavigationFragment extends Fragment {

    private static final String TAG = LocationActiviey.class.getName();

    private BNRoutePlanNode mBNRoutePlanNode = null;

    private IBNRouteGuideManager mRouteGuideManager;

    private Context mContext;

    public void setContext(Context context) {
        mContext = context;
    }

    public void setStartNode(BNRoutePlanNode node) {
        mBNRoutePlanNode = node;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        createHandler();
        mRouteGuideManager = BaiduNaviManagerFactory.getRouteGuideManager();
        View view = mRouteGuideManager.onCreate((Activity) mContext,mOnNavigationListener);
        routeGuideEvent();
        if (view != null) {
            return view;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mRouteGuideManager.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mRouteGuideManager.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mRouteGuideManager.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mRouteGuideManager.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRouteGuideManager.onDestroy(false);
        EventHandler.getInstance().disposeDialog();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        BNRouteGuideManager.getInstance().onConfigurationChanged(newConfig);
    }

    // 导航过程事件监听
    private void routeGuideEvent() {
        EventHandler.getInstance().getDialog(mContext);
        EventHandler.getInstance().showDialog();
        BaiduNaviManagerFactory.getRouteGuideManager().setRouteGuideEventListener(
                new IBNRouteGuideManager.IRouteGuideEventListener() {
                    @Override
                    public void onCommonEventCall(int what, int arg1, int arg2, Bundle bundle) {
                        EventHandler.getInstance().handleNaviEvent(what, arg1, arg2, bundle);
                    }
                }
        );
    }

    private Handler hd = null;
    private static final int MSG_RESET_NODE = 3;
    private void createHandler() {
        if (hd == null) {
            hd = new Handler(mContext.getMainLooper()) {
                public void handleMessage(android.os.Message msg) {
                    if (msg.what == MSG_RESET_NODE) {
                        mRouteGuideManager.resetEndNodeInNavi(
                                new BNRoutePlanNode(116.21142, 40.85087, "百度大厦11",
                                        null, CoordinateType.GCJ02));
                    }
                }
            };
        }
    }


    private IBNRouteGuideManager.OnNavigationListener mOnNavigationListener =
            new IBNRouteGuideManager.OnNavigationListener() {

                @Override
                public void onNaviGuideEnd() {
                    // 退出导航
//                    finish();
                }

                @Override
                public void notifyOtherAction(int actionType, int arg1, int arg2, Object obj) {
                    if (actionType == 0) {
                        // 导航到达目的地 自动退出
                        Log.i(TAG, "notifyOtherAction actionType = " + actionType + ",导航到达目的地！");
                        mRouteGuideManager.forceQuitNaviWithoutDialog();
                    }
                }
            };
}
