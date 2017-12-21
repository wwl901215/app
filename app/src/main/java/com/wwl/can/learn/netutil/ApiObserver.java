package com.wwl.can.learn.netutil;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by wangwenliang on 2017/12/21.
 */

public abstract class ApiObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull T t) {
        success(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        error(e);
    }

    @Override
    public void onComplete() {

    }

    public abstract void success(T t);

    public abstract void error(Throwable e);

}
