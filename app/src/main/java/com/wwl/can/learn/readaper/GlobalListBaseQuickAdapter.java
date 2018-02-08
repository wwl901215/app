package com.wwl.can.learn.readaper;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.wwl.can.R;
import com.wwl.can.learn.bean.GlobalListItemBean;

import java.util.List;

/**
 * https://www.jianshu.com/p/b343fcff51b0
 * Created by wangwenliang on 2018/2/8.
 */

public class GlobalListBaseQuickAdapter extends BaseQuickAdapter<GlobalListItemBean, BaseViewHolder> {

    Context context;

    public GlobalListBaseQuickAdapter(Context con,@LayoutRes int layoutResId, @Nullable List<GlobalListItemBean> data) {
        super(layoutResId, data);
        this.context = con;
    }

    @Override
    protected void convert(BaseViewHolder helper, GlobalListItemBean item) {
        helper.setText(R.id.tv_title,item.getTitle());
        String strHtml = item.getPrice() != null ? item.getPrice() + "<font size='15px' color='red'><small> ¥</small></font>" : "";
        helper.setText(R.id.tv_price, Html.fromHtml(strHtml));
        Picasso.with(context)
                .load(item.getImgUrl())
                .placeholder(R.mipmap.picture)
                .error(R.mipmap.voice_grey)
                .into((ImageView) helper.getView(R.id.iv));
        //为了防止item中edittext内容错乱
        final EditText editText = (EditText) helper.getView(R.id.et);
        editText.setTag(item);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //获得edittext所在position里面的bean，并设置数据
                GlobalListItemBean bean = (GlobalListItemBean) editText.getTag();
                if (s != null && !TextUtils.isEmpty(s)) {
                    bean.setInputText(s + "");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if (item.getInputText() != null && !TextUtils.isEmpty(item.getInputText())) {
            editText.setText(item.getInputText());
        }else {
            editText.setText("");
        }

    }
}
