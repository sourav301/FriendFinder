package com.example.sourav.twitterapp.api;

import com.example.sourav.twitterapp.model.gitmodel;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by Sourav on 6/26/2015.
 */
public interface twitterApi {

    @Headers({
            "User-Agent:{user}"
    })
    @GET("/users/{user}")
    public void getFeed(@Path("user") String user, Callback<gitmodel> response);

}
