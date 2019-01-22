package com.wwl.can;

import android.app.Application;
import android.os.Environment;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.navisdk.adapter.IBaiduNaviManager;
import com.wwl.can.learn.netutil.OkhttpUtils;
import com.baidu.navisdk.adapter.BaiduNaviManagerFactory;

import java.io.File;

public class BaseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        OkhttpUtils.initInstanse();//初始化网络请求对象
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
//        SDKInitializer.initialize(getApplicationContext());
    }
}
