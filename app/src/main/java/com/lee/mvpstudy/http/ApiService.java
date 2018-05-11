package com.lee.mvpstudy.http;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("geocoding")
    Observable<String> getLocation(@Query("a") String a);//获取的请求结果为String

}
