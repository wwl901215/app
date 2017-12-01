package com.wwl.can.learn.cadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 封装的公共adapter
 * Created by wangwenliang on 2017/12/1.
 */

public abstract class CommonAdapter<T> extends BaseAdapter {

    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;
    private OnItemChildViewClickListener itemChildViewClick;

    public CommonAdapter(Context context, List<T> mDatas,int itemLayoutId){
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = getViewHolder(position,convertView,parent);
        convert(viewHolder,getItem(position));
        return viewHolder.getConvertView();
    }

    public abstract void convert(ViewHolder helper, T item);

    //给外部提供点击事件的方法
    public void setOnItemChildViewClick(OnItemChildViewClickListener listener){
        this.itemChildViewClick = listener;
    }

    public interface OnItemChildViewClickListener {
        void onChildClick(View view);//单次点击
        void onChildLongClick(View view);//长按点击
    }

    private ViewHolder getViewHolder(int position,View convertView,ViewGroup parent){
        return ViewHolder.get(mContext,convertView,parent,mItemLayoutId,position,itemChildViewClick);
    }

}
