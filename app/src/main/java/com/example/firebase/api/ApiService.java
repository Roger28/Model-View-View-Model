package com.example.firebase.api;

import com.example.firebase.ent.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiService {
    @Headers({
            "Accept: application/json",
            "Content-type: application/json"
    })

    @GET("youtube/home_feed")
    Call<Example> getAllData();

}
