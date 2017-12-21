package com.wwl.can.learn.netutil;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.wwl.can.learn.test.Constant;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wangwenliang on 2017/12/21.
 */

public class ServiceGenerator {

    private static Retrofit.Builder builder = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(Constant.URL_BASE);

    public static <T> T createService(Class<T> serviceClass){

        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        //// TODO: 2017/12/21 这里可以添加缓存过滤，网络请求过滤等等
//        okBuilder.cache(setCache());//设置缓存
//        okBuilder.interceptors().add(getCache(60));//获取缓存

//        okBuilder.interceptors().add(headerInterceptor());//设置头部
        okBuilder.connectTimeout(20, TimeUnit.SECONDS);
        okBuilder.writeTimeout(20,TimeUnit.SECONDS);
        okBuilder.readTimeout(20,TimeUnit.SECONDS);
//        okBuilder.retryOnConnectionFailure(true);//错误重连
        builder.client(okBuilder.build());
        return builder.build().create(serviceClass);
    }

    /**
     * 头部过滤器
     * @return
     */
    private static Interceptor headerInterceptor(){
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .header("","")
                        .header("","")
                        .header("","")
                        .method(originalRequest.method(),originalRequest.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
        return interceptor;
    }

    /**
     * 文件缓存到本地
     * @return
     */
    private static Cache setCache(){
        File cacheFile = new File("cachefillpath","httpCahce");
        Cache cache = new Cache(cacheFile, 1024*1024*50);
        return cache;
    }


    /**
     * 获取本地缓存
     * @param maxCacheTime 最大超时时间
     * @return
     */
    private static Interceptor getCache(final int maxCacheTime){
        final Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (false){ //网络不可用状态
                    request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }else { //网络可用状态
                    request.newBuilder()
                            .cacheControl(CacheControl.FORCE_NETWORK)
                            .build();
                }

                Response newResponse = chain.proceed(request);
                if (false){//网络不可用状态
                    int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                    newResponse= newResponse.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }else {//网络可用状态
                    newResponse = newResponse.newBuilder()
                            //覆盖服务器响应头的Cache-Control,用我们自己的,因为服务器响应回来的可能不支持缓存
                            .header("Cache-Control", "public,max-age=" + maxCacheTime)
                            .removeHeader("Pragma")
                            .build();
                }
                return newResponse;
            }
        };
        return interceptor;
    }

}
