package com.wwl.can.learn.netutil;

import android.content.Context;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by wangwenliang on 2017/12/17.
 */

public abstract class ApiObserver<T> implements Observer<T> {
    public WeakReference<Context> contextWeakReference;
    public ApiObserver(Context context){
        contextWeakReference = new WeakReference<Context>(context);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }


    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
