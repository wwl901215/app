package com.wwl.can.learn.readaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.wwl.can.R;
import com.wwl.can.learn.bean.GlobalListItemBean;
import com.wwl.can.learn.viewholder.GlobalLiseItemRVH;

import java.util.List;

/**
 * Created by wangwenliang on 2018/2/6.
 */

public class GlobalListAdapter extends RecyclerView.Adapter<GlobalLiseItemRVH> implements View.OnClickListener {
    private Context context;
    private List mData;
    private OnItemClickListener clickListener = null;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(List data) {
        this.mData = data;
    }

    @Override
    public GlobalLiseItemRVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.global_list_item, parent, false);
        GlobalLiseItemRVH holder = new GlobalLiseItemRVH(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(GlobalLiseItemRVH holder, int position) {
        GlobalListItemBean item = (GlobalListItemBean) mData.get(position);
        holder.titleTV.setText(item.getTitle());
        String strHtml = item.getPrice() != null ? item.getPrice() + "<font size='15px' color='red'><small> ¥</small></font>" : "";
        holder.priceTV.setText(Html.fromHtml(strHtml));
        Picasso.with(context)
                .load(item.getImgUrl())
                .placeholder(R.mipmap.picture)
                .error(R.mipmap.voice_grey)
                .into(holder.iv);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onClick(View v) {
        if (clickListener != null) {
            clickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClick(OnItemClickListener listener) {
        this.clickListener = listener;
    }
}
