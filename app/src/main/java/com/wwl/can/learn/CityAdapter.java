package com.wwl.can.learn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.wwl.can.R;

import java.util.List;

/**
 * Created by wangwenliang on 2017/12/1.
 */

public class CityAdapter extends BaseAdapter{
    private Context mContext;
    private List<String> data;
    private OnAllClickListener allClickListener;

    public CityAdapter(Context context, List<String> list) {
        mContext = context;
        data = list;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.learn_item,null);
            viewHolder.tv = (TextView) convertView.findViewById(R.id.tv_learn);
            viewHolder.bt = (Button) convertView.findViewById(R.id.iv_learn);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv.setText(data.get(position));
        viewHolder.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allClickListener.itemClick(v);
            }
        });
        viewHolder.bt.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                allClickListener.itemLongClick(v);
                return true;
            }
        });
        viewHolder.bt.setTag(position);
        return convertView;
    }


    public void setAllClickListener(OnAllClickListener listener){
        this.allClickListener = listener;
    }

    interface OnAllClickListener{
        void itemClick(View view);
        void itemLongClick(View view);
    }

    public static class ViewHolder{
        TextView tv;
        Button bt;
    }
}
