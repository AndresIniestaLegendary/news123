package com.example.a24811.news123;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by 24811 on 2017/11/10.
 */

public interface Api2 {
    @GET("news/{index}")
    Call<NewsMainBean> getNewMain(@Path("index") String index);
}
