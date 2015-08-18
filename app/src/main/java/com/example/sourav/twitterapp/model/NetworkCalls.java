package com.example.sourav.twitterapp.model;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Sourav on 8/18/2015.
 */
public class NetworkCalls {

    OkHttpClient client = new OkHttpClient();

    //GET request to the URL provided
    public String getData(String URL){
        String responseString="";
        try {
            responseString = run(URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
