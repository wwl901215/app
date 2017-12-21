package com.wwl.can.learn.netutil;

import com.wwl.can.learn.bean.BookMessage;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wangwenliang on 2017/12/19.
 */

public interface BookService {

    @GET("/v2/book/search")
    Observable<BookMessage> searchBookMessage(
            @Query(value = "q") String searchKey,
            @Query(value = "count") int count,
            @Query(value = "start") int start
    );
}
