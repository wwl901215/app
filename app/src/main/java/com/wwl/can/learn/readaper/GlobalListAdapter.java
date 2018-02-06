package com.wwl.can.learn.readaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.wwl.can.R;
import com.wwl.can.learn.bean.GlobalListItemBean;
import com.wwl.can.learn.viewholder.GlobalLiseItemRVH;

import java.util.List;

/**
 * Created by wangwenliang on 2018/2/6.
 */

public class GlobalListAdapter extends RecyclerView.Adapter<GlobalLiseItemRVH> {
    private Context context;
    private List mData;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(List data) {
        this.mData = data;
    }

    @Override
    public GlobalLiseItemRVH onCreateViewHolder(ViewGroup parent, int viewType) {
        GlobalLiseItemRVH holder = new GlobalLiseItemRVH(LayoutInflater.from(context).inflate(R.layout.global_list_item,parent,false));
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
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
