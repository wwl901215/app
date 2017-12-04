package com.wwl.can.wifi.unit;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wwl.can.wifi.adapter.WifiScanResultAdapter;

/**
 * Created by wangwenliang on 2017/12/4.
 */

public class SomeMethod {
    public static void setListViewHeightBasedOnChilren(ListView listView) {
        if (listView == null) return;
        WifiScanResultAdapter adapter = (WifiScanResultAdapter) listView.getAdapter();
        if (adapter == null) return;
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i,null,listView);
            listItem.measure(0,0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
