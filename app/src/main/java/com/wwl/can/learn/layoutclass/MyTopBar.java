package com.wwl.can.learn.layoutclass;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wwl.can.R;
import com.wwl.can.learn.utils.Utils;

/**
 * @version V1.0
 * @Created by: 艾克斯 .
 * @Date: 2018-02-14
 * @Package: com.wwl.can.learn.layoutclass
 * @Description: ${TODO}
 * @author: 王文亮
 * @邮箱： wwl901215@163.com
 */

public class MyTopBar extends RelativeLayout {
    Button leftBtn;
    Button rightBtn;
    TextView tvTitle;

    public MyTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.my_top_bar,this,true);
        leftBtn = (Button) findViewById(R.id.top_left_btn);
        rightBtn = (Button) findViewById(R.id.top_right_btn);
        tvTitle = (TextView) findViewById(R.id.top_text);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTopBar);
        if (typedArray != null) {
            //设置背景颜色
            // 设置是否显示左边按钮标签
            boolean leftButtonShow = typedArray.getBoolean(R.styleable.MyTopBar_left_button_visible,true);
            if (!leftButtonShow){
                leftBtn.setVisibility(View.INVISIBLE);
            }else {
                leftBtn.setVisibility(View.VISIBLE);
            }

            //设置左边文字
            String leftBtnText = typedArray.getString(R.styleable.MyTopBar_left_button_text);
            if (!TextUtils.isEmpty(leftBtnText)) {
                leftBtn.setText(leftBtnText);
                int color = typedArray.getColor(R.styleable.MyTopBar_left_button_text_color, Color.RED);
                leftBtn.setTextColor(color);
            }else {
                int resourceId1 = typedArray.getResourceId(R.styleable.MyTopBar_left_button_drawable, R.mipmap.arrow_gray_n);
                if (resourceId1 != -1) {
                    leftBtn.setBackgroundResource(resourceId1);
                }
            }

            //设置中间标题文字内容
            int titleDrawableID = typedArray.getResourceId(R.styleable.MyTopBar_title_text_drawable, -1);
            if (titleDrawableID != -1) {
                tvTitle.setBackgroundResource(titleDrawableID);
            }else {
                String titleText = typedArray.getString(R.styleable.MyTopBar_title_text);
                tvTitle.setText(titleText);
                int titleColorID = typedArray.getColor(R.styleable.MyTopBar_title_text_color, Color.BLACK);
                tvTitle.setTextColor(titleColorID);
            }

            Boolean showRight = typedArray.getBoolean(R.styleable.MyTopBar_right_button_visible,true);
            if (!showRight) {
                rightBtn.setVisibility(View.INVISIBLE);
            }else {
                rightBtn.setVisibility(View.VISIBLE);
            }

            String rightText = typedArray.getString(R.styleable.MyTopBar_right_button_text);
            if (!TextUtils.isEmpty(rightText)) {
                rightBtn.setText(rightText);
                int rightTextColor = typedArray.getColor(R.styleable.MyTopBar_right_button_text_color, Color.BLACK);
                rightBtn.setTextColor(rightTextColor);
            }else {
                int rightPicID = typedArray.getResourceId(R.styleable.MyTopBar_right_button_drawable, R.mipmap.wifiopen);
                rightBtn.setBackgroundResource(rightPicID);
            }
            typedArray.recycle();
        }
    }

    public void setOnTitleClick(OnClickListener listener) {
        if (listener != null) {
            leftBtn.setOnClickListener(listener);
            rightBtn.setOnClickListener(listener);
        }
    }
}
