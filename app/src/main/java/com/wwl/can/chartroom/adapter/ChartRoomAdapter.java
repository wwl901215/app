package com.wwl.can.chartroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wwl.can.R;
import com.wwl.can.chartroom.bean.ChartRoomItemBean;

import java.util.List;

/**
 * Created by wangwenliang on 2017/12/8.
 */

public class ChartRoomAdapter extends BaseAdapter {

    private List<ChartRoomItemBean> data;
    private Context context;

    public ChartRoomAdapter(Context context, List<ChartRoomItemBean> data){
        this.data = data;
        this.context = context;
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
    public int getItemViewType(int position) {
        return data.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        ViewHolder1 viewHolder1 = null;
        ChartRoomItemBean bean = data.get(position);
        int type = bean.getType();
        if (convertView == null){
            if (type == 1){
                viewHolder = new ViewHolder();
                convertView =  LayoutInflater.from(context).inflate(R.layout.chart_room_listitem_left,null);
                viewHolder.nickname = (TextView) convertView.findViewById(R.id.chart_item_nickname);
                viewHolder.chartcontent = (TextView) convertView.findViewById(R.id.chart_item_content);
                convertView.setTag(R.layout.chart_room_listitem_left,viewHolder);
            }else {
                viewHolder1 = new ViewHolder1();
                convertView =  LayoutInflater.from(context).inflate(R.layout.chart_room_listitem_right,null);
                viewHolder1.nickname = (TextView) convertView.findViewById(R.id.chart_item_nickname);
                viewHolder1.chartcontent = (TextView) convertView.findViewById(R.id.chart_item_content);
                convertView.setTag(R.layout.chart_room_listitem_right,viewHolder1);
            }

        }else {
            if (type == 1){
                viewHolder = (ViewHolder) convertView.getTag(R.layout.chart_room_listitem_left);
            }else {
                viewHolder1 = (ViewHolder1) convertView.getTag(R.layout.chart_room_listitem_right);
            }
        }

        if (type == 1){
            viewHolder.nickname.setText(bean.getNickname());
            viewHolder.chartcontent.setText(bean.getChartcontent());
        }else {
            viewHolder1.nickname.setText(bean.getNickname());
            viewHolder1.chartcontent.setText(bean.getChartcontent());
        }


        return convertView;
    }

    class ViewHolder{
        private TextView nickname;
        private TextView chartcontent;
    }

    class ViewHolder1{
        private TextView nickname;
        private TextView chartcontent;
    }
}
