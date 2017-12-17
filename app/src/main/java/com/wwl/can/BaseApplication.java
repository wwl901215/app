package com.wwl.can;

import android.app.Application;

import com.wwl.can.learn.netutil.OkhttpUtils;

public class BaseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        OkhttpUtils.initInstanse();//初始化网络请求对象
    }
}
