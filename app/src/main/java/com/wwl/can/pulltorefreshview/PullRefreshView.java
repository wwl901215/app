package com.wwl.can.pulltorefreshview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * @version V1.0
 * @Created by: 艾克斯 .
 * @Date: 2018-07-08
 * @Package: com.wwl.can.pulltorefreshview
 * @Description: 自定义下拉刷新组件
 * @author: 王文亮
 * @邮箱： wwl901215@163.com
 */

public class PullRefreshView extends LinearLayout {
    private Context mContext;
    private PullHeaderView mHeaderView;
    private int mHeaderViewHeight;
    private PullFootView mFootView;
    private int mFootViewHeight;

    public PullRefreshView(Context context) {
        this(context,null);
    }

    public PullRefreshView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PullRefreshView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOrientation(LinearLayout.VERTICAL);
        this.mContext = context;
    }

    /**
     * 添加头部刷新View
     */
    private void addHeaderView() {
        mHeaderView=new PullHeaderView(mContext);
        mHeaderViewHeight=mHeaderView.getHeaderHeight();
        mHeaderView.setGravity(Gravity.BOTTOM);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, mHeaderViewHeight);
        // 设置topMargin的值为负的header View高度,隐藏在最上方
        params.topMargin = -mHeaderViewHeight;
        addView(mHeaderView, params);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //添加footView,
        addFootView();
        //获取AdapterView
        initContentAdapterView();
    }

    /**
     * 添加FootView
     */
    private void addFootView() {
        mFootView=new PullFootView(mContext);
        mFootViewHeight=mFootView.getFootViewHeight();
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, mFootViewHeight);
        mFootView.setGravity(Gravity.CENTER_HORIZONTAL);
        addView(mFootView, params);
    }

    /**
     * 获取AdapterView
     */
    private void initContentAdapterView() {
        int count=getChildCount();
        if(count<2){
            throw new IllegalArgumentException("this layout must contain 2 child views,and AdapterView or ScrollView must in the second position!");
        }
        View view=null;
        for (int i = 0; i < count; i++) {
            view=getChildAt(i);
            if(view instanceof AdapterView<?>){
                mAdapterView= (AdapterView<?>) view;
                /**
                 * 只针对ListView做上拉加载操作
                 */
                ListView lv= (ListView) mAdapterView;
                /**
                 * 设置ListView的滚动监听
                 */
                lv.setOnScrollListener(this);
            }else if(view instanceof ScrollView){
                mScrollerView = (ScrollView) view;
            }
        }
        if (mAdapterView == null && mScrollerView == null) {
            throw new IllegalArgumentException("must contain a AdapterView or ScrollView in this layout!");
        }
    }
}
