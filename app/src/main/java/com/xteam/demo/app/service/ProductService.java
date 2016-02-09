package com.xteam.demo.app.service;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ProductService {

    @GET("search")
    Observable<Response<ResponseBody>> getProducts(
            @Query("limit") int limit,
            @Query("skip") int skip,
            @Query("q") String tags,
            @Query("onlyInStock") boolean onlyInStock
    );

}
