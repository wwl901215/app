package com.wwl.can.learn.netutil;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.wwl.can.learn.test.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wangwenliang on 2017/12/21.
 */

public class ServiceGenerator {

    private static Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(Constant.URL_BASE)
                    .build();

    public static <T> T createService(Class<T> serviceClass){
        return retrofit.create(serviceClass);
    }

}
