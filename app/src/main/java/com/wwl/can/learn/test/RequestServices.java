package com.wwl.can.learn.test;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by wangwenliang on 2017/12/18.
 */

public interface RequestServices {

    @GET("basil2style")
    Call<Bean> getString();
}
