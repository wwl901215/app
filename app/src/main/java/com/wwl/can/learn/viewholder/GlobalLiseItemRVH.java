package com.wwl.can.learn.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wwl.can.R;

/**
 * Created by wangwenliang on 2018/2/6.
 */

public class GlobalLiseItemRVH extends RecyclerView.ViewHolder {
    public ImageView iv;
    public TextView titleTV;
    public TextView priceTV;
    public GlobalLiseItemRVH(View itemView) {
        super(itemView);
        titleTV = (TextView) itemView.findViewById(R.id.tv_title);
        priceTV = (TextView) itemView.findViewById(R.id.tv_price);
        iv = (ImageView) itemView.findViewById(R.id.iv);
    }
}
