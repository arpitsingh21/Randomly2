package com.example.randomly2.retrofit;


import com.example.randomly2.data.PostResponsePojo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("59b3f0b0100000e30b236b7e")
    Call<PostResponsePojo> getPosts1();
    @GET("59ac28a9100000ce0bf9c236")
    Call<PostResponsePojo> getPosts2();
    @GET("59ac293b100000d60bf9c239")
    Call<PostResponsePojo> getPosts3();
}
