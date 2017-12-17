package com.wwl.can.learn.netutil;

import android.content.Context;

import io.reactivex.annotations.NonNull;

/**
 * Created by wangwenliang on 2017/12/17.
 */

public abstract class ApiResultObserver<T> extends ApiObserver<T> {
    public ApiResultObserver(Context context) {
        super(context);
    }

    @Override
    public void onNext(@NonNull T t) {
        onSuccess(t);
    }
    public abstract void onSuccess(T apiResult);
}
