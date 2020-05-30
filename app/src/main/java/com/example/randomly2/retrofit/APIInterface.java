package com.example.randomly2.retrofit;


import com.example.randomly2.data.PostResponsePojo;

import retrofit2.Callback;
import retrofit2.http.GET;

public interface APIInterface {


    @GET("59b3f0b0100000e30b236b7e")
    public void getPosts1(Callback<PostResponsePojo> callback);

    @GET("59ac28a9100000ce0bf9c236")
    public void getPosts2(Callback<PostResponsePojo> callback);

    @GET("59ac293b100000d60bf9c239")
    public void getPosts3(Callback<PostResponsePojo> callback);
}
