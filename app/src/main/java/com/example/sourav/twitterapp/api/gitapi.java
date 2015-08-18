package com.example.sourav.twitterapp.api;

import com.example.sourav.twitterapp.model.gitmodel;

import retrofit.*;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by Sourav on 6/26/2015.
 */
public interface gitapi {
    @Headers({
            "User-Agent:{user}"
    })
    @GET("/users/{user}")      //here is the other url part.best way is to start using /
    public void getFeed(@Path("user") String user, Callback<gitmodel> response);     //string user is for passing values from edittext for eg: user=basil2style,google
    //response is the response from the server which is now in the POJO
}
