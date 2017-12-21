package com.wwl.can.learn.netutil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wangwenliang on 2017/12/21.
 */

public class BaseSubscribe {

    public void subscribe(Observable observable, Observer observer){
                observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
