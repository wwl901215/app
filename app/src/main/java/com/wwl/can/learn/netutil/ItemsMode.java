package com.wwl.can.learn.netutil;

import android.content.Context;

import com.wwl.can.learn.bean.BookList;

import io.reactivex.Observable;

/**
 * Created by wangwenliang on 2017/12/17.
 */

public class ItemsMode extends BaseMode {
    public ItemsMode(Context context) {
        super(context);
    }

    public void getBookListItems(ApiObserver<BookList> observer){
        ItemsApi apiService = ServiceGenerator.createService(ItemsApi.class);
        Observable<BookList> observable = apiService.getBookListData();
        subscribe(observable,observer);
    }
}
