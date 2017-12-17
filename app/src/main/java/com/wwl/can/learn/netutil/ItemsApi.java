package com.wwl.can.learn.netutil;

import com.wwl.can.learn.bean.BookList;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * api 接口
 * Created by wangwenliang on 2017/12/17.
 */

public interface ItemsApi {


    @GET("/v2/book/1220562")
    Observable<BookList> getBookListData();
}
