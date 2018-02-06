package com.wwl.can.learn.netutil;

import com.wwl.can.learn.bean.BookMessage;
import com.wwl.can.learn.bean.GlobalListBean;
import com.wwl.can.learn.bean.GlobalListMoreBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

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

    @GET("/cms/pages/relation/pageV1")
    Observable<GlobalListBean> getGlobalListData(@QueryMap Map<String, Object> params);

    @GET("/cms/pages/relation/nextPageV1")
    Observable<GlobalListMoreBean> getGlobalListMoreData(@QueryMap Map<String, Object> params);

}
