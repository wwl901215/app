package com.wwl.can;

import android.app.Application;
import android.os.Environment;

import com.baidu.navisdk.adapter.IBaiduNaviManager;
import com.wwl.can.learn.netutil.OkhttpUtils;
import com.baidu.navisdk.adapter.BaiduNaviManagerFactory;

import java.io.File;

public class BaseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        OkhttpUtils.initInstanse();//初始化网络请求对象
    }
}
