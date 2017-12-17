package com.wwl.can.learn.netutil;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wangwenliang on 2017/12/17.
 */

public class ServiceGenerator {
    private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(Api.HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());


    public static <S> S createService(Class<S> serviceClass) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder builder = addHeader(original,null);
                Request request = builder.build();
                return chain.proceed(request);
            }
        });
        client.connectTimeout(20, TimeUnit.SECONDS);
        client.writeTimeout(20, TimeUnit.SECONDS);
        client.readTimeout(20, TimeUnit.SECONDS);
        client.addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY));
        builder.client(client.build());
        return builder.build().create(serviceClass);
    }

    private static Request.Builder addHeader(Request original, String authToken){
        return original.newBuilder()
                .header("x-access-token","aak")
                .header("someKey","someValue")
                .addHeader("Accept","dont understand header and addheader")
                .method(original.method(),original.body());
    }

}
