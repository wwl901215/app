package com.wwl.can.pulltorefreshview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.wwl.can.learn.utils.Utils;

/**
 * @version V1.0
 * @Created by: 艾克斯 .
 * @Date: 2018-07-08
 * @Package: com.wwl.can.pulltorefreshview
 * @Description: ${TODO}
 * @author: 王文亮
 * @邮箱： wwl901215@163.com
 */

public class PullFootView extends LinearLayout {
    private Context mContext;
    private LinearLayout footView;
    private ProgressBar mProgressBar;
    private int footHeight;
    public PullFootView(Context context) {
        this(context,null);
    }

    public PullFootView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullFootView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 初始化
     * @param context
     */
    private void initView(Context context) {
        this.mContext=context;
        this.setOrientation(LinearLayout.HORIZONTAL);
        footView=new LinearLayout(mContext);
        mProgressBar=new ProgressBar(mContext,null,android.R.attr.progressBarStyleSmall);
        LinearLayout.LayoutParams progressLp=new LinearLayout.LayoutParams(-2,-2);
        progressLp.gravity= Gravity.CENTER;
        progressLp.width= Utils.dp2px(mContext,40);
        progressLp.height=Utils.dp2px(mContext,40);
        footView.addView(mProgressBar,progressLp);
        LinearLayout.LayoutParams footLp=new LinearLayout.LayoutParams(-2,-2);
        footLp.gravity=Gravity.CENTER;
        footView.setGravity(Gravity.CENTER);
        this.addView(footView,footLp);
        Utils.measureView(this);
        footHeight=this.getMeasuredHeight();
    }

    /**
     * 获取footview的高度
     * @return
     */
    public int getFootViewHeight() {
        return footHeight;
    }
    private int mSate;
    public void setState(int state) {
        if(mSate==state){
            return;
        }
        if(state==PullHeaderView.STATE_REFRESHING||state==PullHeaderView.STATE_READY){
            this.setVisibility(View.VISIBLE);
        }else{
            this.setVisibility(View.GONE);
        }
        mSate=state;
    }
}
