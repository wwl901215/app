package com.wwl.can.learn.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by wangwenliang on 2018/2/7.
 */

public class Utils {
    public static boolean isRecyclerViewBottom(RecyclerView recyclerView, int y) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int state = recyclerView.getScrollState();
        if (visibleItemCount > 0 && lastVisibleItemPosition == totalItemCount - 1 && state == RecyclerView.SCROLL_STATE_IDLE) {
            return true;
        }else {
            return false;
        }
    }
}
