package com.wwl.can.learn.netutil;

import com.wwl.can.learn.bean.BookMessage;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by wangwenliang on 2017/12/19.
 */

public interface BookService {

    @GET("/v2/book/search?q=android&count=1&start=0")
    Observable<BookMessage> searchBookMessage();
}
