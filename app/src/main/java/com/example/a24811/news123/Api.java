package com.example.a24811.news123;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by 24811 on 2017/11/7.
 */

public interface Api {
    @GET("news/{type}/page/{page}")
    Call<NewsListBean> getNewsList(@Path("type") int type, @Path("page") int page);
}
