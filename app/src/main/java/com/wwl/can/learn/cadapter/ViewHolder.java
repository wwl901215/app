package com.wwl.can.learn.cadapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 配合公共CommonAdapter使用的ViewHolder;
 * 目前功能还不完善，可以主动添加功能;
 * Created by wangwenliang on 2017/12/1.
 */

public class ViewHolder {


    private final SparseArray<View> mViews;
    private  int mPosition;
    private View mConvertView;
    private CommonAdapter.OnItemChildViewClickListener clickListener;

    private ViewHolder(Context context,ViewGroup parent,int layoutId, int position,CommonAdapter.OnItemChildViewClickListener listener){
        this.clickListener = listener;
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        mConvertView.setTag(this);
    }

    /**
     * 获取ViewHolder对象
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position, CommonAdapter.OnItemChildViewClickListener listener){
        if (convertView == null){
            return new ViewHolder(context,parent,layoutId,position,listener);
        }
        return (ViewHolder) convertView.getTag();
    }

    public View getConvertView(){
        return mConvertView;
    }

    public int getPosition(){
        return mPosition;
    }

    /**
     * 通过控件的id获取控件，如果没有则加入views
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId){
        View view = mViews.get(viewId);
        if (view == null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }


//***********************************************************************下面的方法是给itemview负值，可以手动添加其它负值方法******************************

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text)
    {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }


    public ViewHolder setButtonClick(int viewId){
        Button view = getView(viewId);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null){
                    clickListener.onChildClick(v);
                }
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (clickListener != null){
                    clickListener.onChildLongClick(v);
                }
                return true;
            }
        });
        view.setTag(mPosition);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int drawableId)
    {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }


    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param bm
     * @return
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bm)
    {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }


    /**
     * 为ImageView设置图片
     * 网络图片请求
     *
     * @param
     * @param
     * @return
     */
//    public ViewHolder setImageByUrl(int viewId, String url)
//    {
//        ImageLoader.getInstance(3, Type.LIFO).loadImage(url,
//                (ImageView) getView(viewId));
//        return this;
//    }




}
