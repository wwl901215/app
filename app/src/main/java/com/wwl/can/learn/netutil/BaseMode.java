package com.wwl.can.learn.netutil;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 订阅基类
 * Created by wangwenliang on 2017/12/17.
 */

public class BaseMode implements IModel {
    protected Context mContext;

    public BaseMode(Context context) {
        mContext = context;
    }

    public Observable subscribe(Observable mObservable, Observer observer){
        mObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return mObservable;
    }

}
