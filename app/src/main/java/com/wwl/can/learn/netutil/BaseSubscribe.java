package com.wwl.can.learn.netutil;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wangwenliang on 2017/12/21.
 */

public class BaseSubscribe {

    private Context context;

    public BaseSubscribe(Context context) {
        this.context = context;
    }

    private LifecycleProvider getLifecycleProvider() {
        LifecycleProvider provider = null;
        if (null != context && context instanceof LifecycleProvider) {
            provider = (LifecycleProvider) context;
        }
        return provider;
    }

    public void subscribe(Observable observable, Observer observer){
                observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(getLifecycleProvider().bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(observer);
    }
}
