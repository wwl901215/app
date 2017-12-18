package com.wwl.can.learn.test;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by wangwenliang on 2017/12/18.
 */

public interface MyService {

    @GET("basil2style")
    Observable<Bean> getBean();
}
